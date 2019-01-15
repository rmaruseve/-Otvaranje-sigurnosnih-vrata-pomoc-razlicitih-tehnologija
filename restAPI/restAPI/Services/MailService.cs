using Microsoft.Extensions.Options;
using restAPI.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Threading.Tasks;

namespace restAPI.Services
{
    public interface IMailService
    {
        int Send(string reciever, string body, string subject);
    }

    public class MailService : IMailService
    {
        private readonly AppSettings _appSettings;

        public MailService(IOptions<AppSettings> appSettings)
        {
            _appSettings = appSettings.Value;
        }

        public int Send(string reciever, string body, string subject)
        {
            SmtpClient client = new SmtpClient(_appSettings.MailHost, _appSettings.MailPort);
            client.EnableSsl = true;
            client.UseDefaultCredentials = false;
            client.Credentials = new NetworkCredential(_appSettings.MailUsername, _appSettings.MailPassword);

            MailMessage mailMessage = new MailMessage();
            mailMessage.From = new MailAddress(_appSettings.MailUsername);
            mailMessage.To.Add(reciever);
            mailMessage.Body = body;
            mailMessage.Subject = subject;
            client.Send(mailMessage);
            return 1;
        }
    }
}
