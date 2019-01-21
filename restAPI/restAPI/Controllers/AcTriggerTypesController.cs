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
    public class AcTriggerTypesController : ControllerBase
    {
        private readonly mydbContext _context;

        public AcTriggerTypesController(mydbContext context)
        {
            _context = context;
        }

        // GET: api/AcTriggerTypes
        [HttpGet]
        public IEnumerable<AcTriggerType> GetAcTriggerType()
        {
            return _context.AcTriggerType;
        }

        // GET: api/AcTriggerTypes/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetAcTriggerType([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var acTriggerType = await _context.AcTriggerType.FindAsync(id);

            if (acTriggerType == null)
            {
                return NotFound();
            }

            return Ok(acTriggerType);
        }

        // PUT: api/AcTriggerTypes/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAcTriggerType([FromRoute] int id, [FromBody] AcTriggerType acTriggerType)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != acTriggerType.TrtId)
            {
                return BadRequest();
            }

            _context.Entry(acTriggerType).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AcTriggerTypeExists(id))
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

        // POST: api/AcTriggerTypes
        [HttpPost]
        public async Task<IActionResult> PostAcTriggerType([FromBody] AcTriggerType acTriggerType)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.AcTriggerType.Add(acTriggerType);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAcTriggerType", new { id = acTriggerType.TrtId }, acTriggerType);
        }

        // DELETE: api/AcTriggerTypes/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAcTriggerType([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var acTriggerType = await _context.AcTriggerType.FindAsync(id);
            if (acTriggerType == null)
            {
                return NotFound();
            }

            _context.AcTriggerType.Remove(acTriggerType);
            await _context.SaveChangesAsync();

            return Ok(acTriggerType);
        }

        private bool AcTriggerTypeExists(int id)
        {
            return _context.AcTriggerType.Any(e => e.TrtId == id);
        }
    }
}