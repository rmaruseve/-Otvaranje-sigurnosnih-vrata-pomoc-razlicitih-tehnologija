using System;
using System.Collections.Generic;

namespace db.Db
{
    public partial class Schedule
    {
        public int SchProId { get; set; }
        public int SchDayId { get; set; }
        public TimeSpan SchTimeFrom { get; set; }
        public TimeSpan SchTimeTo { get; set; }

        public Day SchDay { get; set; }
        public Profil SchPro { get; set; }
    }
}
