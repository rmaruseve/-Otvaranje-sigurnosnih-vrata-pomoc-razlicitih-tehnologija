using data.Json;
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
        List<AcAccess> checkAccess(int? userId, int objId);
        List<AcAccess> GetByUser(int id);
        AcAccess Create(AccessDto acs);
        AcAccess Update(AccessDto acs);
        void Delete(int id);
    }

    public class AccessService : IAccessService
    {
        private mydbContext _context;

        public AccessService(mydbContext context, IMailService mailService)
        {
            _context = context;
        }

        public List<AcAccess> checkAccess(int? userId, int objId)
        {
            List<AcAccess> access = (
                from acs in _context.AcAccess
                where acs.AcsUsr.UsrId == userId && acs.AcsObj.ObjId == objId && acs.AcsValidFrom <= DateTime.Now && acs.AcsValidTo >= DateTime.Now
                select acs
            ).ToList();

            return access;
        }

        public List<AcAccess> GetByUser(int id)
        {
            List<AcAccess> acss = (
                from acs in _context.AcAccess
                where acs.AcsUsrId == id
                select acs
            ).ToList();
            return acss;
        }

        public AcAccess Create(AccessDto acs)
        {
            AcAccess acsNew = new AcAccess
            {
                AcsValidFrom = acs.ValidFrom,
                AcsValidTo = acs.ValidTo,
                AcsOpeningCounter = acs.Counter != null ? acs.Counter : -1,
                AcsUsrId = acs.UsrId,
                AcsProId = acs.ProId,
                AcsObjId = acs.ObjId
            };
            _context.AcAccess.Add(acsNew);
            _context.SaveChanges();
            return acsNew;
        }

        public AcAccess Update(AccessDto acs)
        {
            AcAccess acsUp = new AcAccess
            {
                AcsId = acs.AcsId,
                AcsValidFrom = acs.ValidFrom,
                AcsValidTo = acs.ValidTo,
                AcsOpeningCounter = acs.Counter != null ? acs.Counter : -1,
                AcsUsrId = acs.UsrId,
                AcsProId = acs.ProId,
                AcsObjId = acs.ObjId
            };
            _context.AcAccess.Update(acsUp);
            _context.SaveChanges();
            return acsUp;
        }

        public void Delete(int id)
        {
            var acs = _context.AcAccess.Find(id);
            if (acs != null)
            {
                _context.AcAccess.Remove(acs);
                _context.SaveChanges();
            }
        }
    }
}
