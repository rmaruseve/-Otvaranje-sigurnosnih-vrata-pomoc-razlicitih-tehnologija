using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using data.Json;
using db.Db;
using Microsoft.AspNetCore.Authorization;

namespace restAPI.Controllers
{
    [Route("api/[controller]")]
    [Authorize]
    [ApiController]
    public class TriggerAccessController : ControllerBase
    {

        private readonly mydbContext _context;
        //private IUserService _userService;
        //private IMapper _mapper;
        //private readonly AppSettings _appSettings;

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
        /*[HttpPost]
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
        }*/

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
    }
}
