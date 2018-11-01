using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class EventStatus
    {
        public EventStatus()
        {
            EventLog = new HashSet<EventLog>();
        }

        public int EvsId { get; set; }
        public string EvsName { get; set; }

        public ICollection<EventLog> EventLog { get; set; }
    }
}
