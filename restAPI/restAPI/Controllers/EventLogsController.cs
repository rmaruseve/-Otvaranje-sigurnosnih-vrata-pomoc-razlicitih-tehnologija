﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using data.Json;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using db.Db;
using Microsoft.AspNetCore.Authorization;
using Newtonsoft.Json;
using restAPI.Helpers;
using restAPI.Services;

namespace restAPI.Controllers
{
    [Route("api/[controller]")]
    //[Authorize]
    [ApiController]
    public class EventLogsController : ControllerBase
    {
        private readonly mydbContext _context;
        private IEventLogService _eventLogService;

        public EventLogsController(
            IEventLogService eventLogService
            ,mydbContext context)
        {
            _eventLogService = eventLogService;
            _context = context;
        }

        // GET: api/EventLogs
        [HttpGet]
        public IEnumerable<AcEventLog> GetAcEventLog()
        {
            return _context.AcEventLog;
        }

        //// GET: api/EventLogs
        //[HttpGet]
        //public ActionResult<string> GetAcEventLog([FromBody] FilterEventLogDto req)
        //{
        //    try
        //    {
        //        List<AcEventLog> evLog = _eventLogService.getEventLogs(req);
        //        return Ok(
        //            JsonConvert.SerializeObject(new
        //            {
        //                eventLog = evLog
        //            })
        //        );
                
        //    }
        //    catch (AppException ex)
        //    {
        //        return BadRequest(new {message = ex.Message});
        //    }
        //}

        // GET: api/EventLogs/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetAcEventLog([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var acEventLog = await _context.AcEventLog.FindAsync(id);

            if (acEventLog == null)
            {
                return NotFound();
            }

            return Ok(acEventLog);
        }

        // PUT: api/EventLogs/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAcEventLog([FromRoute] int id, [FromBody] AcEventLog acEventLog)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != acEventLog.EvlId)
            {
                return BadRequest();
            }

            _context.Entry(acEventLog).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AcEventLogExists(id))
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
        public async Task<IActionResult> PostAcEventLog([FromBody] AcEventLog acEventLog)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.AcEventLog.Add(acEventLog);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAcEventLog", new { id = acEventLog.EvlId }, acEventLog);
        }

        // DELETE: api/EventLogs/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAcEventLog([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var acEventLog = await _context.AcEventLog.FindAsync(id);
            if (acEventLog == null)
            {
                return NotFound();
            }

            _context.AcEventLog.Remove(acEventLog);
            await _context.SaveChangesAsync();

            return Ok(acEventLog);
        }

        private bool AcEventLogExists(int id)
        {
            return _context.AcEventLog.Any(e => e.EvlId == id);
        }
    }
}