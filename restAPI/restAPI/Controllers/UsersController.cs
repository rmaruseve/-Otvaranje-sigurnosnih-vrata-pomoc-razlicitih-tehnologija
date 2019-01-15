using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Security.Claims;
using Microsoft.AspNetCore.Authorization;
using restAPI.Helpers;
using data.Json;
using db.Db;
using restAPI.Services;
using AutoMapper;
using Microsoft.Extensions.Options;
using System.IdentityModel.Tokens.Jwt;
using System.Text;
using Microsoft.IdentityModel.Tokens;
using Newtonsoft.Json;

namespace restAPI.Controllers
{
    [Route("api/[controller]")]
    //[Authorize]
    [ApiController]
    public class UsersController : ControllerBase
    {
        private readonly mydbContext _context;
        private IUserService _userService;
        private ITriggerService _triggerService;
        private IAccessService _accessService;
        private IMailService _mailService;
        private IMapper _mapper;
        private readonly AppSettings _appSettings;
        
        public UsersController(
            IUserService userService,
            ITriggerService triggerService,
            IAccessService accessService,
            IMailService mailService,
            IMapper mapper,
            mydbContext context,
            IOptions<AppSettings> appSettings)
        {
            _context = context;
            _userService = userService;
            _triggerService = triggerService;
            _accessService = accessService;
            _mailService = mailService;
            _mapper = mapper;
            _appSettings = appSettings.Value;
        }
        /// <summary>
        /// User autentication
        /// </summary>
        /// <param name="userDto"></param>
        /// <returns></returns>
        /// <response code="400">Username or password is incorrect</response>
        [AllowAnonymous]
        [HttpPost("authenticate")]
        [ProducesResponseType(typeof(List<UserDto>), 200)]
        public IActionResult Authenticate([FromBody]UserDto userDto)
        {
            var user = _userService.Authenticate(userDto.UsrEmail, userDto.LoginPassword);

            if (user == null)
                return BadRequest(new { message = "Username or password is incorrect" });

            var tokenHandler = new JwtSecurityTokenHandler();
            var key = Encoding.ASCII.GetBytes("JWTSTRINGJWTSTRINGJWTSTRINGJWTSTRING");
            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(new Claim[]
                {
                    new Claim(ClaimTypes.Name, user.UsrId.ToString())
                }),
                Expires = DateTime.UtcNow.AddDays(7),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
            };
            var token = tokenHandler.CreateToken(tokenDescriptor);
            var tokenString = tokenHandler.WriteToken(token);
            //Console.WriteLine(user.UsrRol);

            // return basic user info (without password) and token to store client side
            return Ok(JsonConvert.SerializeObject(new
            {
                Id = user.UsrId,
                Email = user.UsrEmail,
                FirstName = user.UsrName,
                LastName = user.UsrSurname,
                Token = tokenString,
                Role = user.UsrRolId
            }));
        }


        /// <summary>
        ///  Register new user
        /// </summary>
        /// <returns></returns>
        /// <response code="400">return error message if there was an exception</response>
        [HttpPost("register")]
        public IActionResult Register([FromBody]UserDto userDto)
        {
            // map dto to entity
            var userReq = _mapper.Map<AcUser>(userDto);
            try
            {
                // if admin
                string currentId = User.FindFirst("current_user_id")?.Value;
                if(currentId != null)
                {
                    var userId = int.Parse(currentId);
                    AcUser currentUsr = _userService.GetById(userId);
                }
                //if (currentUsr.UsrRol.RolName != "Administrator")
                //    throw new AppException("User not admin.");
                // save 
                AcUser user = new AcUser();
                if(userDto.UsrEmail != null)
                {
                    string randPassword = Functions.RandString(8);
                    user = _userService.Create(userReq, randPassword);
                    _mailService.Send(user.UsrEmail, "Your password is: " + randPassword, "Mobilisis User Account");

                } else
                {
                    List<AcTrigger> trgs = _triggerService.GetByValue(userDto.PhoneNumber);
                    if (trgs.Count > 0)
                        throw new AppException("Phone number already exists.");
                    userReq.UsrEmail = userDto.PhoneNumber;
                    string randPassword = Functions.RandString(8);
                    user = _userService.Create(userReq, randPassword);
                    _triggerService.Create(user.UsrId, "Sms", userDto.PhoneNumber);
                    _triggerService.Create(user.UsrId, "Phone", userDto.PhoneNumber);
                    // send sms
                }
                return Ok(user.UsrId);
            }
            catch (AppException ex)
            {
                // return error message if there was an exception
                return BadRequest(new { message = ex.Message });
            }
        }

        /// <summary>
        ///  
        /// </summary>
        /// <returns></returns>
        /// <response code="400">return error message if there was an exception</response> 
        [HttpGet]
        [ProducesResponseType(typeof(List<UserDto>), 200)]
        [ProducesResponseType(400)]
        public IActionResult GetAll()
        {
            try
            {
                var userId = int.Parse(User.FindFirst("current_user_id")?.Value);
                AcUser user = _userService.GetById(userId);
                if (user.UsrRolId != 1)
                    throw new AppException("User not admin.");
                var users = _userService.GetAll();
                var userDtos = _mapper.Map<IList<UserDto>>(users);
                return Ok(userDtos);
            }
            catch (AppException ex)
            {
                // return error message if there was an exception
                return BadRequest(new { message = ex.Message });
            }
        }
        // GET: api/AcUsers
        /// <summary>
        /// return all users
        /// </summary>
        /// <returns></returns>
        //[AllowAnonymous]
        [HttpGet("All")]
        public IEnumerable<AcUser> GetAcUsers()
        {
            return _context.AcUser;
        }

        [HttpGet("currentUser")]
        public IActionResult GetCurrentUser()
        {
            var userId = int.Parse(User.FindFirst("current_user_id")?.Value);
            var user = _userService.GetById(userId);
            var userDto = _mapper.Map<UserDto>(user);
            return Ok(userDto);
        }

        [HttpGet("{id}")]
        public IActionResult GetById(int id)
        {
            var user = _userService.GetById(id);
            var userDto = _mapper.Map<UserDto>(user);
            return Ok(userDto);
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="userDto"></param>
        /// <returns></returns>
        /// <response code="200">If user is updated</response>
        /// <response code="400">If the item is null</response>
        [HttpPost("update")]
        [ProducesResponseType(200)]
        [ProducesResponseType(400)]
        public IActionResult Update([FromBody]UserDto userDto)
        {
            // map dto to entity and set id
            var userReq = _mapper.Map<AcUser>(userDto);
            try
            {
                var userId = int.Parse(User.FindFirst("current_user_id")?.Value);
                AcUser user = _userService.GetById(userId);
                if (user.UsrRol.RolName == "Administrator")
                    throw new AppException("User not admin.");
                // save 
                _userService.Update(userReq, userDto.LoginPassword);
                return Ok();
            }
            catch (AppException ex)
            {
                // return error message if there was an exception
                return BadRequest(new { message = ex.Message });
            }
        }

        /// <summary>
        /// Deletes a specific User.
        /// </summary>
        /// <param name="id"></param>  
        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            _userService.Delete(id);
            return Ok();
        }
    }
}