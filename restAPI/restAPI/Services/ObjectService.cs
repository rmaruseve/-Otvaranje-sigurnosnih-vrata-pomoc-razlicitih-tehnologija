using db.Db;
using restAPI.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace restAPI.Services
{
    public interface IObjectService
    {
        AcObject getObject(string type, string objectName, int userId);
    }

    public class ObjectService : IObjectService
    {
        private mydbContext _context;

        public ObjectService(mydbContext context)
        {
            _context = context;
        }

        public AcObject getObject(string type, string objectName, int userId)
        {
            AcObject acObject = (
                from obj in _context.AcObject
                join ohs in _context.AcObjectHasTriggerType on obj.ObjId equals ohs.OhtObjId
                join trgt in _context.AcTriggerType on ohs.OhtTrtId equals trgt.TrtId
                where trgt.TrtName == type && obj.ObjName == objectName
                select obj
            ).SingleOrDefault();

            if(acObject == null)
            {
                throw new AppException("Object not found.");
            }
            else if(acObject.ObjActivity == 0)
            {
                throw new AppException("Object not found.");
            }
            else if(acObject.ObjAuto == 0)
            {
                throw new AppException("Can't open object.");
            }
            else if (acObject.ObjOpen == 1)
            {
                throw new AppException("Object already open.");
            }

            AcAccess access = (
                from acs in _context.AcAccess
                where acs.AcsUsr.UsrId == userId && acs.AcsObj.ObjId == acObject.ObjId && acs.AcsValidFrom <= DateTime.Now && acs.AcsValidTo >= DateTime.Now
                select acs
            ).SingleOrDefault();

            if(access == null)
            {
                throw new AppException("User has no access.");
            }

            return acObject;
        }
    }
}
