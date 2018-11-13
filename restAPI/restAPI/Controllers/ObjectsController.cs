using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using db.Db;

namespace restAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ObjectsController : ControllerBase
    {
        private readonly mydbContext _context;

        public ObjectsController(mydbContext context)
        {
            _context = context;
        }

        // GET: api/Objects
        [HttpGet]
        public IEnumerable<db.Db.Object> GetObject()
        {
            return _context.Object;
        }

        // GET: api/Objects/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetObject([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var @object = await _context.Object.FindAsync(id);

            if (@object == null)
            {
                return NotFound();
            }

            return Ok(@object);
        }

        // PUT: api/Objects/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutObject([FromRoute] int id, [FromBody] db.Db.Object @object)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != @object.ObjId)
            {
                return BadRequest();
            }

            _context.Entry(@object).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ObjectExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Objects
        [HttpPost]
        public async Task<IActionResult> PostObject([FromBody] db.Db.Object @object)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Object.Add(@object);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetObject", new { id = @object.ObjId }, @object);
        }

        // DELETE: api/Objects/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteObject([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var @object = await _context.Object.FindAsync(id);
            if (@object == null)
            {
                return NotFound();
            }

            _context.Object.Remove(@object);
            await _context.SaveChangesAsync();

            return Ok(@object);
        }

        private bool ObjectExists(int id)
        {
            return _context.Object.Any(e => e.ObjId == id);
        }
    }
}