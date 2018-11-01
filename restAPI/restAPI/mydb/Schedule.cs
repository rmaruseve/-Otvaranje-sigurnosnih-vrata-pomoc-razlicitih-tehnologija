using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class Schedule
    {
        public int SchProId { get; set; }
        public int SchTimId { get; set; }
        public int SchDayId { get; set; }

        public Day SchDay { get; set; }
        public Profil SchPro { get; set; }
        public TimePeriod SchTim { get; set; }
    }
}
