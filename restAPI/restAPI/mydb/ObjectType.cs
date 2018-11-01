using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class ObjectType
    {
        public ObjectType()
        {
            Object = new HashSet<Object>();
        }

        public int ObtId { get; set; }
        public string ObtName { get; set; }
        public byte? ObtIn { get; set; }
        public byte? ObtOut { get; set; }

        public ICollection<Object> Object { get; set; }
    }
}
