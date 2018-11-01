using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class Access
    {
        public int AcsId { get; set; }
        public DateTime AcsValidFrom { get; set; }
        public DateTime? AcsValidTo { get; set; }
        public int? AcsOpeningCounter { get; set; }
        public int? AcsUsrId { get; set; }
        public int? AcsProId { get; set; }
        public int? AcsObjId { get; set; }

        public Object AcsObj { get; set; }
        public Profil AcsPro { get; set; }
        public User AcsUsr { get; set; }
    }
}
