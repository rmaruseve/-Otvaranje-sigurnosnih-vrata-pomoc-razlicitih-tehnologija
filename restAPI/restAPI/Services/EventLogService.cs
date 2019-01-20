using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using data.Json;
using db.Db;

namespace restAPI.Services
{
    public interface IEventLogService
    {
        List<FilterEventLogDto> getEventLogs(int? id);
    }

    public class EventLogService : IEventLogService
    {
        private mydbContext _context;

        public EventLogService(mydbContext context)
        {
            _context = context;
        }
        public List<FilterEventLogDto> getEventLogs(int? id)
        {
            List <FilterEventLogDto> eventLogs = (
                from evl in _context.AcEventLog
                join evs in _context.AcEventStatus on evl.EvlEvsId equals evs.EvsId
                join obj in _context.AcObject on evl.EvlObjId equals obj.ObjId
                join usr in _context.AcUser on evl.EvlUsrId equals usr.UsrId
                join trt in _context.AcTriggerType on evl.EvlTrtId equals trt.TrtId
                where id == null || evl.EvlUsrId==id 
                select new FilterEventLogDto
                {
                    EventLogId = evl.EvlId,
                    Date = evl.EvlDate,
                    TriggerValue = evl.EvlTrgValue,
                    UserName = usr.UsrName,
                    UserSurname = usr.UsrSurname,
                    TriggerName = trt.TrtName,
                    ObjectName = obj.ObjName,
                    EventStatusName = evs.EvsName
                }
            ).ToList();

            return eventLogs;
        }

    }
}
