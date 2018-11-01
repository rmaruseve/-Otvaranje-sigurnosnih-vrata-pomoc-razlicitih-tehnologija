using System;
using System.Collections.Generic;

namespace restAPI.mydb
{
    public partial class Object
    {
        public Object()
        {
            Access = new HashSet<Access>();
            EventLog = new HashSet<EventLog>();
            ObjectHasTriggerType = new HashSet<ObjectHasTriggerType>();
        }

        public int ObjId { get; set; }
        public string ObjName { get; set; }
        public byte? ObjOpen { get; set; }
        public byte? ObjAuto { get; set; }
        public byte ObjActivity { get; set; }
        public string ObjGps { get; set; }
        public string ObjAction { get; set; }
        public int ObjObtTypeId { get; set; }

        public ObjectType ObjObtType { get; set; }
        public ICollection<Access> Access { get; set; }
        public ICollection<EventLog> EventLog { get; set; }
        public ICollection<ObjectHasTriggerType> ObjectHasTriggerType { get; set; }
    }
}
