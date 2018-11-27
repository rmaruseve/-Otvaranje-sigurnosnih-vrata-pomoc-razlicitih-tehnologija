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
    }
}
