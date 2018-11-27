using db.Db;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace restAPI.Services
{
    public interface IObjectService
    {
        IEnumerable<AcObject> GetObjectsByUser();
        IEnumerable<AcObject> GetObjectsByTrigger(int id);
        AcObject Create(AcObject objectToCreate);
        void Delete(int id);
    }


    public class ObjectService : IObjectService
    {
        private mydbContext _context;

        public ObjectService(mydbContext context)
        {
            _context = context;
        }

        public AcObject Create(AcObject objectToCreate)
        {
            throw new NotImplementedException();
        }

        public void Delete(int id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<AcObject> GetObjectsByTrigger(int id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<AcObject> GetObjectsByUser()
        {
            throw new NotImplementedException();
        }

        public AcUser getUserByTriggerType(string value, string type)
        {
            AcUser user = (
                from us in _context.AcUser
                join trg in _context.AcTrigger on us.UsrId equals trg.TrgUsrId
                join trgt in _context.AcTriggerType on trg.TrgTrtId equals trgt.TrtId
                where trg.TrgValue == value && trgt.TrtName == type
                select us
            ).SingleOrDefault();
            return user;
        }

        public List<AcObject> getObjectsByTrigger(string type, string objectName)
        {
            List<AcObject> objects = (
                from obj in _context.AcObject
                join ohs in _context.AcObjectHasTriggerType on obj.ObjId equals ohs.OhtObjId
                join trgt in _context.AcTriggerType on ohs.OhtTrtId equals trgt.TrtId
                where trgt.TrtName == type && obj.ObjName == objectName
                select obj
            ).ToList();
            return objects;
        }
    }
}
