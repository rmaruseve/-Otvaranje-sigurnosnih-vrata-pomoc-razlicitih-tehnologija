using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class Trigger
    {
        public int TrgUsrId { get; set; }
        public int TrgCatId { get; set; }
        public string TrgValue { get; set; }
        public byte TrgActivity { get; set; }

        public TriggerType TrgCat { get; set; }
        public User TrgUsr { get; set; }
    }
}
