using System;
using System.Data.Linq;
using System.Linq;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class User
    {
        public User()
        {
            Access = new HashSet<Access>();
            EventLog = new HashSet<EventLog>();
            Trigger = new HashSet<Trigger>();
        }

        public int UsrId { get; set; }
        public string UsrName { get; set; }
        public string UsrSurname { get; set; }
        public string UsrEmail { get; set; }
        public byte UsrActivity { get; set; }
        public string UsrPassword { get; set; }
        public string UsrCryptedPassword { get; set; }
        public int? UsrRolId { get; set; }

        public Role UsrRol { get; set; }
        public ICollection<Access> Access { get; set; }
        public ICollection<EventLog> EventLog { get; set; }
        public ICollection<Trigger> Trigger { get; set; }

        public User getUserByTriggerType(string value, string type)
        {
            using (var ctx = new mydbContext())
            {
                User user = (
                    from us in ctx.User
                    join trg in ctx.Trigger on us.UsrId equals trg.TrgUsrId
                    join trgt in ctx.TriggerType on trg.TrgCatId equals trgt.TrtId
                    where trg.TrgValue == value && trgt.TrtName == type
                    select us
                ).Single();
                return user;
            }
        }
    }
}
