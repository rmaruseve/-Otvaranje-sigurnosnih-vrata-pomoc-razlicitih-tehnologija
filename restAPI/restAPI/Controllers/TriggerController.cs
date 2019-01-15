using AutoMapper;
using data.Json;
using db.Db;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Options;
using restAPI.Helpers;
using restAPI.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace restAPI.Controllers
{
    [Route("api/[controller]")]
    [Authorize]
    [ApiController]
    public class TriggerController : ControllerBase
    {

        private readonly mydbContext _context;
        private ITriggerService _triggerService;
        private IMapper _mapper;
        private IUserService _userService;
        private readonly AppSettings _appSettings;

        public TriggerController(
            IMapper mapper,
            mydbContext context,
            ITriggerService triggerService,
            IUserService userService,
            IOptions<AppSettings> appSettings)
        {
            _context = context;
            _mapper = mapper;
            _userService = userService;
            _triggerService = triggerService;
            _appSettings = appSettings.Value;
        }

        /// <summary>
        /// Create triggers for user.
        /// </summary>
        /// <response code="400">return error message if there was an exception</response>  
        [HttpPost]
        public IActionResult Create([FromBody]TriggerTypeValueDto trgDto)
        {
            try
            {
                // if admin
                var userId = int.Parse(User.FindFirst("current_user_id")?.Value);
                AcUser user = _userService.GetById(userId);
                if (user.UsrRol.RolName != "Administrator")
                    throw new AppException("User not admin.");
                _triggerService.Create(trgDto.userId, trgDto.triggerTypeId, trgDto.triggerValue, (byte)trgDto.trgActivity);
                return Ok();
            }
            catch (AppException ex)
            {
                // return error message if there was an exception
                return BadRequest(new { message = ex.Message });
            }
        }
    }
}
