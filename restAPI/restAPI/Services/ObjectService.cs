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
        List<AcObject> getObjects(string type, string objectName);
    }

    public class ObjectService : IObjectService
    {
        private mydbContext _context;

        public ObjectService(mydbContext context)
        {
            _context = context;
        }

        public List<AcObject> getObjects(string type, string objectName)
        {
            List<AcObject> objs = new List<AcObject>();
            AcObject sObj = (
                from obj in _context.AcObject
                join ohs in _context.AcObjectHasTriggerType on obj.ObjId equals ohs.OhtObjId
                join trgt in _context.AcTriggerType on ohs.OhtTrtId equals trgt.TrtId
                where trgt.TrtName == type && obj.ObjName == objectName
                select obj
            ).SingleOrDefault();
            objs.Add(sObj);

            return objs;
        }
    }
}
