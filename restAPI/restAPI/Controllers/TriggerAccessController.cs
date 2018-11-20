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
    public class TriggerAccessController : ControllerBase
    {

        private readonly mydbContext _context;

        public TriggerAccessController(mydbContext context)
        {
            _context = context;
        }

        // GET api/values
        [HttpGet]
        public ActionResult<string> Get()
        {
            List<AcUser> korisnici;
            List<string> proba = new List<string>();
            try
            {
                Console.WriteLine("[INF] Start --------------------");
                korisnici = new List<AcUser>();
                korisnici = this.getAllUsers();
                foreach (AcUser currentObject in korisnici)
                {
                    proba.Add(currentObject.UsrName);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("ex -> " + e);
                throw;
            }

            //return JsonConvert.SerializeObject(new
            //{
            //    id = 
            //    access = hasAccess,
            //    objectAccess = objectsHasAcces
            //});
            return JsonConvert.SerializeObject(new
            {
                ime = proba
            });
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
            AcUser user = this.getUserByTriggerType(req.Value, req.TriggerTypeName);
            if (user != null)
            {
                Console.WriteLine(user.UsrName);
                hasAccess = 1;
            } else
            {
                Console.WriteLine("Not found");
            }
            List<AcObject> objects = this.getObjectsByTrigger(req.TriggerTypeName, req.Text);
            if (objects.Count > 0)
            {
                foreach (AcObject currentObject in objects)
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

        public List<AcUser> getAllUsers()
        {
            List<AcUser> users = (
                from obj in _context.AcUser
                select obj
            ).ToList();
            return users;
        }

        public AcUser getUserByTriggerType(string value, string type)
        {
            AcUser user = (
                from us in _context.AcUser
                join trg in _context.AcTrigger on us.UsrId equals trg.TrgUsrId
                join trgt in _context.AcTriggerType on trg.TrgTrtId equals trgt.TrtId
                where trg.TrgValue == value && trgt.TrtName == type
                select us
            ).SingleOrDefault();
            return user;
        }

        

        public List<AcObject> getObjectsByTrigger(string type, string objectName)
        {
            List<AcObject> objects = (
                from obj in _context.AcObject
                join ohs in _context.AcObjectHasTriggerType on obj.ObjId equals ohs.OhtObjId
                join trgt in _context.AcTriggerType on ohs.OhtTrtId equals trgt.TrtId
                where trgt.TrtName == type && obj.ObjName == objectName
                select obj
            ).ToList();
            return objects;
        }
    }
}
