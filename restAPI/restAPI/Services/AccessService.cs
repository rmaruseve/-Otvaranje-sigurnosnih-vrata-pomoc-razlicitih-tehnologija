using db.Db;
using restAPI.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace restAPI.Services
{
    public interface IAccessService
    {
        List<AcAccess> checkAccess(int userId, int objId);
    }

    public class AccessService : IAccessService
    {
        private mydbContext _context;

        public AccessService(mydbContext context)
        {
            _context = context;
        }

        public List<AcAccess> checkAccess(int userId, int objId)
        {
            List<AcAccess> access = (
                from acs in _context.AcAccess
                where acs.AcsUsr.UsrId == userId && acs.AcsObj.ObjId == objId && acs.AcsValidFrom <= DateTime.Now && acs.AcsValidTo >= DateTime.Now
                select acs
            ).ToList();

            return access;
        }
    }
}
