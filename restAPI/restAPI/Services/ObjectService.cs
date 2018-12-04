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
        AcObject getObject(string type, string objectName);
    }

    public class ObjectService : IObjectService
    {
        private mydbContext _context;

        public ObjectService(mydbContext context)
        {
            _context = context;
        }

        public AcObject getObject(string type, string objectName)
        {
            AcObject returnObj = (
                from obj in _context.AcObject
                join ohs in _context.AcObjectHasTriggerType on obj.ObjId equals ohs.OhtObjId
                join trgt in _context.AcTriggerType on ohs.OhtTrtId equals trgt.TrtId
                where trgt.TrtName == type && obj.ObjName == objectName
                select obj
            ).SingleOrDefault();

            return returnObj;
        }
    }
}
