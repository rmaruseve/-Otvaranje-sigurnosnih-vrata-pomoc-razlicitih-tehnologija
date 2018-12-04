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
        AcAccess checkAccess(int userId, int objId);
    }

    public class AccessService : IAccessService
    {
        private mydbContext _context;

        public AccessService(mydbContext context)
        {
            _context = context;
        }

        public AcAccess checkAccess(int userId, int objId)
        {
            AcAccess access = (
                from acs in _context.AcAccess
                where acs.AcsUsr.UsrId == userId && acs.AcsObj.ObjId == objId && acs.AcsValidFrom <= DateTime.Now && acs.AcsValidTo >= DateTime.Now
                select acs
            ).SingleOrDefault();
            if(access == null)
            {
                throw new AppException("User has no access.");
            }
            return access;
        }
    }
}
