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
        //List<AcAccess> Create(UserDto usrDto, int openingCounter, int usrId);
    }

    public class AccessService : IAccessService
    {
        private mydbContext _context;

        public AccessService(mydbContext context)
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

        /*public List<AcAccess> Create(UserDto usrDto, int openingCounter, int usrId)
        {
            List<AcAccess> newAcs = new List<AcAccess>();
            foreach(int objId in usrDto.Objs)
            {
                AcAccess newAc = new AcAccess
                {
                    AcsValidFrom = usrDto.AccessFrom,
                    AcsValidTo = usrDto.AccessTo != null ? usrDto.AccessTo : DateTime.MaxValue,
                    AcsOpeningCounter = openingCounter,
                    AcsUsrId = usrId,
                    AcsObjId = objId
                };
                _context.AcAccess.Add(newAc);
                newAcs.Add(newAc);
            }
            _context.SaveChanges();
            return newAcs;
        }*/
    }
}
