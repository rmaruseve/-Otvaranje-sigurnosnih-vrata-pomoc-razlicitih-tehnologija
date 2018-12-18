using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using data.Json;
using db.Db;
using Microsoft.AspNetCore.Authorization;
using restAPI.Services;
using AutoMapper;
using restAPI.Helpers;
using Microsoft.Extensions.Options;
using System.Net.Http;
using System.Text;

namespace restAPI.Controllers
{
    [Route("api/[controller]")]
    [Authorize]
    [ApiController]
    public class TriggerAccessController : ControllerBase
    {

        private readonly mydbContext _context;
        private IAccessControl _accessControl;
        private IMapper _mapper;
        private readonly AppSettings _appSettings;

        public TriggerAccessController(
            IAccessControl accessControl,
            IMapper mapper,
            mydbContext context,
            IOptions<AppSettings> appSettings)
        {
            _context = context;
            _mapper = mapper;
            _accessControl = accessControl;
            _appSettings = appSettings.Value;
        }

        /// <summary>
        /// Request to open object.
        /// </summary>
        /// <response code="400">return error message if there was an exception</response>  
        [HttpPost]
        [AllowAnonymous]
        [ProducesResponseType(typeof(List<string>), 200)]
        public ActionResult<string> Get([FromBody] TriggerAccessDto req)
        {
            try
            {
                List<string> objs = _accessControl.objectIO(req);
                if(req.TriggerTypeName == "App")
                {
                    var client = new HttpClient();
                    client.PostAsync("http://192.168.0.1:1880/api/open", new StringContent(JsonConvert.SerializeObject(new
                    {
                        objectAccess = objs
                    }), 
                    Encoding.UTF8, "application/json"));
                    return Ok("Success");
                }else
                {
                    return Ok(
                    JsonConvert.SerializeObject(new
                    {
                        objectAccess = objs
                    })
                );
                }
            } catch (AppException ex)
            {
                return BadRequest(new { message = ex.Message });
            }
        }

        // POST api/values
        /*[HttpPost]
        public ActionResult<string> Post([FromBody] TriggerAccess req)
        {
            int hasAccess = 0;
            List<string> objectsHasAcces = new List<string>();
            AcUser user = this.getUserByTriggerType(req.Value, req.TriggerTypeName);
            if (user != null)
            {
                Console.WriteLine(user.UsrName);
                hasAccess = 1;
            } else
            {
                Console.WriteLine("Not found");
            }
            List<AcObject> objects = this.getObjectsByTrigger(req.TriggerTypeName, req.Text);
            if (objects.Count > 0)
            {
                foreach (AcObject currentObject in objects)
                {
                    objectsHasAcces.Add(currentObject.ObjAction);
                }
            } else
            {
                Console.WriteLine("Not found");
                hasAccess = 0;
            }
            return JsonConvert.SerializeObject(new
            {
                access = hasAccess,
                objectAccess = objectsHasAcces
            });
        }*/
    }
}
