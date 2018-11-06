using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using data.Json;
using db.Db;

namespace restAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ValuesController : ControllerBase
    {
        // GET api/values
        [HttpGet]
        public ActionResult<IEnumerable<string>> Get()
        {
            return new string[] { "value1", "value2" };
        }

        // GET api/values/5
        [HttpGet("{id}")]
        public ActionResult<string> Get(int id)
        {
            return "value";
        }

        // POST api/values
        [HttpPost]
        public ActionResult<string> Post([FromBody] TriggerAccess req)
        {
            int hasAccess = 0;
            List<string> objectsHasAcces = new List<string>();
            User user = this.getUserByTriggerType(req.Value, req.TriggerTypeName);
            if (user != null)
            {
                Console.WriteLine(user.UsrName);
                hasAccess = 1;
            } else
            {
                Console.WriteLine("Not found");
            }
            List<db.Db.Object> objects = this.getObjectsByTrigger(req.TriggerTypeName, req.Text);
            if (objects.Count > 0)
            {
                foreach (db.Db.Object currentObject in objects)
                {
                    objectsHasAcces.Add(currentObject.ObjAction);
                }
            } else
            {
                Console.WriteLine("Not found");
                hasAccess = 0;
            }
            return JsonConvert.SerializeObject(new
            {
                access = hasAccess,
                objectAccess = objectsHasAcces
            });
        }

        // PUT api/values/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE api/values/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }

        public User getUserByTriggerType(string value, string type)
        {
            using (var ctx = new mydbContext())
            {
                User user = (
                    from us in ctx.User
                    join trg in ctx.Trigger on us.UsrId equals trg.TrgUsrId
                    join trgt in ctx.TriggerType on trg.TrgCatId equals trgt.TrtId
                    where trg.TrgValue == value && trgt.TrtName == type
                    select us
                ).SingleOrDefault();
                return user;
            }
        }

        public List<db.Db.Object> getObjectsByTrigger(string type, string objectName)
        {
            using (var ctx = new mydbContext())
            {
                List<db.Db.Object> objects = (
                    from obj in ctx.Object
                    join ohs in ctx.ObjectHasTriggerType on obj.ObjId equals ohs.OhtObjId
                    join trgt in ctx.TriggerType on ohs.OhtTrtId equals trgt.TrtId
                    where trgt.TrtName == type && obj.ObjName == objectName
                    select obj
                ).ToList();
                return objects;
            }
        }
    }
}
