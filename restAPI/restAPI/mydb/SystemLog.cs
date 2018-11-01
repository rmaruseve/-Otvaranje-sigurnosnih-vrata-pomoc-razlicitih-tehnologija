using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class SystemLog
    {
        public int SysId { get; set; }
        public int SysUsrId { get; set; }
        public string SysAction { get; set; }
        public string SysText { get; set; }
    }
}
