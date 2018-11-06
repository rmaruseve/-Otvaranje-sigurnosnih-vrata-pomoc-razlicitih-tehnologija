using System;
using System.Collections.Generic;

namespace db.Db
{
    public partial class Role
    {
        public Role()
        {
            User = new HashSet<User>();
        }

        public int RolId { get; set; }
        public string RolName { get; set; }
        public string RolCompany { get; set; }

        public ICollection<User> User { get; set; }
    }
}
