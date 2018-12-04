using db.Db;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace restAPI.Services
{
    public interface IAccessService
    {
        AcAccess checkAccess(AcUser user, AcObject obj);
    }

    public class AccessService : IAccessService
    {
        private mydbContext _context;

        public AccessService(mydbContext context)
        {
            _context = context;
        }

        public AcAccess checkAccess(AcUser user, AcObject obj)
        {
            AcAccess access = (
                from acs in _context.AcAccess
                where acs.AcsUsr.UsrId == user.UsrId && acs.AcsObj.ObjId == obj.ObjId && acs.AcsValidFrom <= DateTime.Now && acs.AcsValidTo >= DateTime.Now
                select acs
            ).SingleOrDefault();
            return access;
        }
    }
}
