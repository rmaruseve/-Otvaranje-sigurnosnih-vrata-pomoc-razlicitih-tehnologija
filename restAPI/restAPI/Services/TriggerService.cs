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
        void Create(int userId, string triggerType, string triggerValue);
        void Create(int userId, int triggerTypeId, string triggerValue);
        List<AcTrigger> GetByValue(string triggerValue);
    }

    public class TriggerService : ITriggerService
    {
        private mydbContext _context;

        public TriggerService(mydbContext context)
        {
            _context = context;
        }

        public List<AcTrigger> GetByValue(string triggerValue)
        {
            List<AcTrigger> trgs = (
                from trg in _context.AcTrigger
                join trgt in _context.AcTriggerType on trg.TrgTrtId equals trgt.TrtId
                where trg.TrgValue == triggerValue
                select trg
            ).ToList();
            return trgs;
        }

        public void Create(int userId, string triggerType, string triggerValue)
        {
            _context.AcTrigger.Add(new AcTrigger{
                TrgUsrId = userId,
                TrgTrtId = (from trgt in _context.AcTriggerType where trgt.TrtName == triggerType select trgt.TrtId).Single(),
                TrgValue = triggerValue,
                TrgActivity = 1
            });
            _context.SaveChanges();
        }

        public void Create(int userId, int triggerTypeId, string triggerValue)
        {
            _context.AcTrigger.Add(new AcTrigger
            {
                TrgUsrId = userId,
                TrgTrtId = triggerTypeId,
                TrgValue = triggerValue,
                TrgActivity = 1
            });
            _context.SaveChanges();
        }
    }
}
