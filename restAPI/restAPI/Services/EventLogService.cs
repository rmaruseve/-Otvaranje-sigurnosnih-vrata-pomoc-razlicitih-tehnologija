using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using db.Db;

namespace restAPI.Services
{
    public interface IEventLogService
    {
        List<AcEventLog> getEventLogs(data.Json.FilterEventLogDto inputs);
    }

    public class EventLogService : IEventLogService
    {
        private mydbContext _context;

        public EventLogService(mydbContext context)
        {
            _context = context;
        }
        public List<AcEventLog> getEventLogs(data.Json.FilterEventLogDto inputs)
        {
            List<AcEventLog> eventLogs = new List<AcEventLog>();
            AcEventLog sEvl = (
                from evl in _context.AcEventLog
                join evs in _context.AcEventStatus on evl.EvlEvsId equals evs.EvsId
                join obj in _context.AcObject on evl.EvlObjId equals obj.ObjId
                join usr in _context.AcUser on evl.EvlUsrId equals  usr.UsrId
                join trt in _context.AcTriggerType on evl.EvlTrtId equals  trt.TrtId
                where obj.ObjName == inputs.ObjectName
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
