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
    public class AcProfilsController : ControllerBase
    {
        private readonly mydbContext _context;

        public AcProfilsController(mydbContext context)
        {
            _context = context;
        }

        // GET: api/AcProfils
        [HttpGet]
        public IEnumerable<AcProfil> GetAcProfil()
        {
            return _context.AcProfil;
        }

        // GET: api/AcProfils/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetAcProfil([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var acProfil = await _context.AcProfil.FindAsync(id);

            if (acProfil == null)
            {
                return NotFound();
            }

            return Ok(acProfil);
        }

        // PUT: api/AcProfils/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAcProfil([FromRoute] int id, [FromBody] AcProfil acProfil)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != acProfil.ProId)
            {
                return BadRequest();
            }

            _context.Entry(acProfil).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AcProfilExists(id))
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

        // POST: api/AcProfils
        [HttpPost]
        public async Task<IActionResult> PostAcProfil([FromBody] AcProfil acProfil)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.AcProfil.Add(acProfil);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAcProfil", new { id = acProfil.ProId }, acProfil);
        }

        // DELETE: api/AcProfils/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAcProfil([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var acProfil = await _context.AcProfil.FindAsync(id);
            if (acProfil == null)
            {
                return NotFound();
            }

            _context.AcProfil.Remove(acProfil);
            await _context.SaveChangesAsync();

            return Ok(acProfil);
        }

        private bool AcProfilExists(int id)
        {
            return _context.AcProfil.Any(e => e.ProId == id);
        }
    }
}