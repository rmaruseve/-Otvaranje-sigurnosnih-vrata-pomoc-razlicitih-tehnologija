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
        void Create(AccessDto acs);
        void Update(AccessDto acs);
        void Delete(int id);
    }

    public class AccessService : IAccessService
    {
        private mydbContext _context;
        private IMailService _mailService;

        public AccessService(mydbContext context, IMailService mailService)
        {
            _context = context;
            _mailService = mailService;
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

        public void Create(AccessDto acs)
        {
            _context.AcAccess.Add(new AcAccess
            {
                AcsValidFrom = acs.ValidFrom,
                AcsValidTo = acs.ValidTo,
                AcsOpeningCounter = acs.Counter != null ? acs.Counter : -1,
                AcsUsrId = acs.UsrId,
                AcsProId = acs.ProId,
                AcsObjId = acs.ObjId
            });
            _context.SaveChanges();
        }

        public void Update(AccessDto acs)
        {
            _context.AcAccess.Update(new AcAccess
            {
                AcsId = acs.AcsId,
                AcsValidFrom = acs.ValidFrom,
                AcsValidTo = acs.ValidTo,
                AcsOpeningCounter = acs.Counter != null ? acs.Counter : -1,
                AcsUsrId = acs.UsrId,
                AcsProId = acs.ProId,
                AcsObjId = acs.ObjId
            });
            _context.SaveChanges();
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
