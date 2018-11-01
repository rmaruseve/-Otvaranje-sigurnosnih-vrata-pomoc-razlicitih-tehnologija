using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class TriggerType
    {
        public TriggerType()
        {
            EventLog = new HashSet<EventLog>();
            ObjectHasTriggerType = new HashSet<ObjectHasTriggerType>();
            Trigger = new HashSet<Trigger>();
        }

        public int TrtId { get; set; }
        public string TrtName { get; set; }

        public ICollection<EventLog> EventLog { get; set; }
        public ICollection<ObjectHasTriggerType> ObjectHasTriggerType { get; set; }
        public ICollection<Trigger> Trigger { get; set; }
    }
}
