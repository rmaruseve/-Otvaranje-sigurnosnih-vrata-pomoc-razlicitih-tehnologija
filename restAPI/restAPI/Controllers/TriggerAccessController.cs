﻿using System;
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

namespace restAPI.Controllers
{
    [Route("api/[controller]")]
    [Authorize]
    [ApiController]
    public class TriggerAccessController : ControllerBase
    {

        private readonly mydbContext _context;
        private IObjectService _objectService;
        private IUserService _userService;
        private ILoggerService _loggerService;
        private IAccessService _accessService;
        private IMapper _mapper;
        private readonly AppSettings _appSettings;

        public TriggerAccessController(
            IUserService userService,
            IObjectService objectService,
            IAccessService accessService,
            IMapper mapper,
            mydbContext context,
            IOptions<AppSettings> appSettings)
        {
            _context = context;
            _userService = userService;
            _objectService = objectService;
            _mapper = mapper;
            _accessService = accessService;
            _appSettings = appSettings.Value;
        }

        // GET api/values
        [HttpPost]
        [AllowAnonymous]
        public ActionResult<string> Get([FromBody] TriggerAccessDto req)
        {
            try
            {
                var loggedUserId = User.FindFirst("current_user_id")?.Value;
                int userId;
                if (loggedUserId == null)
                {
                    userId = _userService.getUserByTriggerType(req.Value, req.TriggerTypeName);
                }
                else
                {
                    userId = int.Parse(loggedUserId);
                }

                AcObject obj = _objectService.getObject(req.TriggerTypeName, req.ObjectName);
                AcAccess acs = _accessService.checkAccess(userId, obj.ObjId);

                return JsonConvert.SerializeObject(new
                {
                    access = 1,
                    objectAccess = obj.ObjAction
                });
            } catch (AppException ex)
            {
                return JsonConvert.SerializeObject(new
                {
                    access = 0,
                    objectAccess = ""
                });
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
