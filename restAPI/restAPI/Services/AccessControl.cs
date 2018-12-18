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
            List<AcObject> objs = _objectService.getObjects(inputs.TriggerTypeName, inputs.ObjectName);

            if (usTrg == null)
            {
                _logger.InsertEventLog(inputs.Value, usTrg.TrgtId, null, 1);
                throw new AppException("Trigger type not found.");
            }
            else if(usTrg.TrgActivity == null)
            {
                _logger.InsertEventLog(inputs.Value, usTrg.TrgtId, null, 1);
                throw new AppException("Trigger not found.");
            }
            else if (usTrg.TrgActivity == 0)
            {
                _logger.InsertEventLog(inputs.Value, usTrg.TrgtId, null, 3);
                throw new AppException("Trigger not found.");
            }
            else if (usTrg.UsrActivity == 0)
            {
                _logger.InsertEventLog(inputs.Value, usTrg.TrgtId, null, 4);
                throw new AppException("User not found.");
            }
            else if (objs.Count == 0)
            {
                _logger.InsertEventLog(inputs.Value, usTrg.TrgtId, null, 5);
                throw new AppException("Object not found.");
            }

            foreach(AcObject obj in objs)
            {
                if(obj.ObjActivity == 0)
                {
                    _logger.InsertEventLog(inputs.Value, usTrg.TrgtId, obj.ObjId, 6, usTrg.UsrId);
                    continue;
                }
                List<AcAccess> acs = _accessService.checkAccess(usTrg.UsrId, obj.ObjId);

                if (acs.Count != 0)
                {
                    _logger.InsertEventLog(inputs.Value, usTrg.TrgtId, obj.ObjId, 10, usTrg.UsrId);
                    objectIOs.Add(obj.ObjAction);
                }else
                {
                    _logger.InsertEventLog(inputs.Value, usTrg.TrgtId, obj.ObjId, 7, usTrg.UsrId);
                }
            }

            return objectIOs;
        }
    }
}
