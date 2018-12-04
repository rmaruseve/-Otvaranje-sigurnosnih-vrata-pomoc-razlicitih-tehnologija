using db.Db;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace restAPI.Services
{
   public interface ILoggerService
    {
        IEnumerable<AcEventLog> InsertEventLog();
    }

    public class LoggerService
    {
        private static LoggerService _logger;
        private LoggerService() { }
        private mydbContext _context;

        public LoggerService(mydbContext context)
        {
            _context = context;
        }


        public static LoggerService Instance
        {
            get
            {
                if (_logger == null)
                {
                    _logger = new LoggerService();

                }

                return _logger;
            }
        }

        public void InsertEventLog(string value, int trtId, int objId, int evsId)
        {
            
                AcEventLog eventLog = new AcEventLog();
                eventLog.EvlDate = DateTime.Now();
                eventLog.EvlTrgValue = value;
                eventLog.EvlTrtId = trtId;
                eventLog.EvlObjId = objId;
                eventLog.EvlEvsId = evsId;
                _context.AcEventLog.InsertOnSubmit(eventLog);
                _context.SubmitChanges();
            
        }
        public void InsertEventLog(string value, int trtId, int objId, int evsId, int usrId)
        {
            using (db objDataContext = new db())
            {
                AcEventLog eventLog = new AcEventLog();
                eventLog.EvlDate = DateTime.Now();
                eventLog.EvlTrgValue = value;
                eventLog.EvlTrtId = trtId;
                eventLog.EvlObjId = objId;
                eventLog.EvlEvsId = evsId;
                eventLog.EvlUsrId = usrId;
                objDataContext.AcEventLog.InsertOnSubmit(eventLog);
                objDataContext.SubmitChanges();
            }

        }

    }
}
