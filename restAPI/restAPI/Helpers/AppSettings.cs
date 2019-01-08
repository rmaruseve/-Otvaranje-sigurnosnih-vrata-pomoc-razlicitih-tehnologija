using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace restAPI.Helpers
{
    public class MailSettings
    {
        public string Host;
        public int Port;
        public string Username;
        public string Password;
    }


    public class AppSettings
    {
        public string Secret { get; set; }
        public MailSettings mailSettings { get; set; };
    }
}
