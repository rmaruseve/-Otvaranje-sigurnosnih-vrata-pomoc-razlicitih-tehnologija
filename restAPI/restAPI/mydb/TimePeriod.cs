using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class TimePeriod
    {
        public TimePeriod()
        {
            Date = new HashSet<Date>();
            Schedule = new HashSet<Schedule>();
        }

        public int TimId { get; set; }
        public TimeSpan TimOf { get; set; }
        public TimeSpan TimTo { get; set; }

        public ICollection<Date> Date { get; set; }
        public ICollection<Schedule> Schedule { get; set; }
    }
}
