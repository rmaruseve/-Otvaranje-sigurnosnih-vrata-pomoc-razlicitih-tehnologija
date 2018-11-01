using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class Day
    {
        public Day()
        {
            Schedule = new HashSet<Schedule>();
        }

        public int DayId { get; set; }
        public string DayName { get; set; }

        public ICollection<Schedule> Schedule { get; set; }
    }
}
