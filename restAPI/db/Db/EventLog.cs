using System;
using System.Collections.Generic;

namespace db.Db
{
    public partial class EventLog
    {
        public int EvlId { get; set; }
        public DateTime EvlDate { get; set; }
        public string EvlTrgValue { get; set; }
        public int EvlEvsId { get; set; }
        public int? EvlObjId { get; set; }
        public int? EvlUsrId { get; set; }
        public int EvlTrtId { get; set; }

        public EventStatus EvlEvs { get; set; }
        public Object EvlObj { get; set; }
        public TriggerType EvlTrt { get; set; }
        public User EvlUsr { get; set; }
    }
}
