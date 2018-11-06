using System;
using System.Collections.Generic;

namespace db.Db
{
    public partial class ObjectHasTriggerType
    {
        public int OhtTrtId { get; set; }
        public int OhtObjId { get; set; }
        public byte OhtActivity { get; set; }

        public Object OhtObj { get; set; }
        public TriggerType OhtTrt { get; set; }
    }
}
