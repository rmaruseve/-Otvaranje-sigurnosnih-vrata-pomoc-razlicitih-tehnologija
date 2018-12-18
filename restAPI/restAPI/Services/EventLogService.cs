using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using db.Db;

namespace restAPI.Services
{
    public interface IEventLogService
    {
        //List<AcEventLog> getEventLogs();
    }

    public class EventLogService : IEventLogService
    {
        private mydbContext _context;

        public EventLogService(mydbContext context)
        {
            _context = context;
        }
        public List<AcEventLog> getEventLogs()
        {
            List<AcEventLog> eventLogs = new List<AcEventLog>();
            AcEventLog sEvl = (
                from evl in _context.AcEventLog
                select evl
            ).SingleOrDefault();
            if (sEvl != null)
            {
                eventLogs.Add(sEvl);
            }

            return eventLogs;
        }
    }
}
