using db.Db;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using restAPI.Controllers;
using db.AcessControl;
using restAPI.Helpers;

namespace restAPI.Services
{
    public interface IAccessControl
    {
        List<string> objectIO(data.Json.TriggerAccessDto inputs);
    }

    public class AccessControl : IAccessControl
    {
        private mydbContext _context;
        private IUserService _userService;
        private IAccessService _accessService;
        private IObjectService _objectService;
        private ILoggerService _logger;

        public AccessControl(mydbContext context, IUserService userService, IAccessService accessService, IObjectService objectService, ILoggerService logger)
        {
            _context = context;
            _userService = userService;
            _accessService = accessService;
            _objectService = objectService;
            _logger = logger;
        }

        public List<string> objectIO(data.Json.TriggerAccessDto inputs)
        {
            List<string> objectIOs = new List<string>();
            UserTrigger usTrg = _userService.getUserByTriggerType(inputs.Value, inputs.TriggerTypeName);

            if (usTrg == null)
            {
                throw new AppException("Trigger not found.");
            }
            else if (usTrg.UsrActivity == 0)
            {
                throw new AppException("User not found.");
            }
            else if (usTrg.TrgActivity == 0)
            {
                throw new AppException("Trigger not found.");
            }

            List<AcObject> objs = _objectService.getObjects(inputs.TriggerTypeName, inputs.ObjectName);

            if (objs.Count == 0)
            {
                throw new AppException("Object not found.");
            }

            foreach(AcObject obj in objs)
            {
                List<AcAccess> acs = _accessService.checkAccess(usTrg.UsrId, obj.ObjId);

                if (acs.Count != 0)
                {
                    objectIOs.Add(obj.ObjAction);
                }
            }

            return objectIOs;
        }
    }
}
