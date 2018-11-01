using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class Date
    {
        public int DatId { get; set; }
        public DateTime DatDate { get; set; }
        public byte DatEnabled { get; set; }
        public int DatProId { get; set; }
        public int? DatTimId { get; set; }

        public Profil DatPro { get; set; }
        public TimePeriod DatTim { get; set; }
    }
}
