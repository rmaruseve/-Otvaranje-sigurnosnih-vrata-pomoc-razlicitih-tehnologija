using db.Db;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using restAPI.Controllers;

namespace restAPI.Services
{
    //public interface IAccessControl
    //{

    //}

    public class AccessControl 
    {
        private mydbContext _context;

        public void ValidateInputs(data.Json.TriggerAccessDto inputs)
        {
            //TODO: create interface and logic (getUserByTriggerType, getObject, checkAccess, itd.)
            //add insert into logg (LoggerService)
        }
    }
}
