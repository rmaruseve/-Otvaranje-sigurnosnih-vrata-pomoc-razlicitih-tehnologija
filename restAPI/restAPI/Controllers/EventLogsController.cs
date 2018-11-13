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
    public class EventLogsController : ControllerBase
    {
        private readonly mydbContext _context;

        public EventLogsController(mydbContext context)
        {
            _context = context;
        }

        // GET: api/EventLogs
        [HttpGet]
        public IEnumerable<EventLog> GetEventLog()
        {
            return _context.EventLog;
        }

        // GET: api/EventLogs/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetEventLog([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var eventLog = await _context.EventLog.FindAsync(id);

            if (eventLog == null)
            {
                return NotFound();
            }

            return Ok(eventLog);
        }

        // PUT: api/EventLogs/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutEventLog([FromRoute] int id, [FromBody] EventLog eventLog)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != eventLog.EvlId)
            {
                return BadRequest();
            }

            _context.Entry(eventLog).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EventLogExists(id))
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

        // POST: api/EventLogs
        [HttpPost]
        public async Task<IActionResult> PostEventLog([FromBody] EventLog eventLog)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.EventLog.Add(eventLog);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetEventLog", new { id = eventLog.EvlId }, eventLog);
        }

        // DELETE: api/EventLogs/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteEventLog([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var eventLog = await _context.EventLog.FindAsync(id);
            if (eventLog == null)
            {
                return NotFound();
            }

            _context.EventLog.Remove(eventLog);
            await _context.SaveChangesAsync();

            return Ok(eventLog);
        }

        private bool EventLogExists(int id)
        {
            return _context.EventLog.Any(e => e.EvlId == id);
        }
    }
}