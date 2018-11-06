using System;
using System.Collections.Generic;

namespace db.Db
{
    public partial class Profil
    {
        public Profil()
        {
            Access = new HashSet<Access>();
            Date = new HashSet<Date>();
            Schedule = new HashSet<Schedule>();
        }

        public int ProId { get; set; }
        public string ProName { get; set; }
        public byte ProActivity { get; set; }

        public ICollection<Access> Access { get; set; }
        public ICollection<Date> Date { get; set; }
        public ICollection<Schedule> Schedule { get; set; }
    }
}
