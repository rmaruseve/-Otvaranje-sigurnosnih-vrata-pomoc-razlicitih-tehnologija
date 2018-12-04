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
    public class AvailableObjectsController : ControllerBase
    {
        private readonly mydbContext _context;

        public AvailableObjectsController(mydbContext context)
        {
            _context = context;
        }

        // GET: api/AvailableObjects
        [HttpGet]
        public IEnumerable<AcObject> GetAcObject()
        {
            return _context.AcObject;
        }

        // GET: api/AcObjects/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetAcObject([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var acObject = await _context.AcObject.FindAsync(id);

            if (acObject == null)
            {
                return NotFound();
            }

            return Ok(acObject);
        }

        // PUT: api/AcObjects/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAcObject([FromRoute] int id, [FromBody] AcObject acObject)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != acObject.ObjId)
            {
                return BadRequest();
            }

            _context.Entry(acObject).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AcObjectExists(id))
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

        // POST: api/AcObjects
        [HttpPost]
        public async Task<IActionResult> PostAcObject([FromBody] AcObject acObject)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.AcObject.Add(acObject);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAcObject", new { id = acObject.ObjId }, acObject);
        }

        // DELETE: api/AcObjects/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAcObject([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var acObject = await _context.AcObject.FindAsync(id);
            if (acObject == null)
            {
                return NotFound();
            }

            _context.AcObject.Remove(acObject);
            await _context.SaveChangesAsync();

            return Ok(acObject);
        }

        private bool AcObjectExists(int id)
        {
            return _context.AcObject.Any(e => e.ObjId == id);
        }
    }
}