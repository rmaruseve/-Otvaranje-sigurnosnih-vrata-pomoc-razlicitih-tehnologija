using db.Db;
using restAPI.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace restAPI.Services
{
    public interface ITriggerService
    {
        void CreatePhoneNumber(int userId, string phoneNumber);
        void CheckPhoneNumber(string phoneNumber);
    }

    public class TriggerService : ITriggerService
    {
        private mydbContext _context;

        public TriggerService(mydbContext context)
        {
            _context = context;
        }

        public void CheckPhoneNumber(string phoneNumber)
        {
            List<AcTrigger> trgs = (
                from trg in _context.AcTrigger
                join trgt in _context.AcTriggerType on trg.TrgTrtId equals trgt.TrtId
                where (trgt.TrtName == "Sms" || trgt.TrtName == "Phone") && trg.TrgValue == phoneNumber
                select trg
            ).ToList();
            if(trgs.Count == 2)
            {
                throw new AppException("Phone number already exists.");
            }
        }

        public void CreatePhoneNumber(int userId, string phoneNumber)
        {
            _context.AcTrigger.Add(new AcTrigger{
                TrgUsrId = userId,
                TrgTrt = (from trgt in _context.AcTriggerType where trgt.TrtName == "Sms" select trgt).Single(),
                TrgValue = phoneNumber,
                TrgActivity = 1
            });
            _context.AcTrigger.Add(new AcTrigger
            {
                TrgUsrId = userId,
                TrgTrt = (from trgt in _context.AcTriggerType where trgt.TrtName == "Phone" select trgt).Single(),
                TrgValue = phoneNumber,
                TrgActivity = 1
            });
            _context.SaveChanges();
        }
    }
}
