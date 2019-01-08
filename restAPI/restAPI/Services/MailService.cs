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
        int Send(string reciever, string body, string subject, MailSettings mailSettings);
    }

    public class MailService : IMailService
    {
        public int Send(string reciever, string body, string subject, MailSettings mailSettings)
        {
            SmtpClient client = new SmtpClient(mailSettings.Host, mailSettings.Port);
            client.UseDefaultCredentials = false;
            client.Credentials = new NetworkCredential(mailSettings.Username, mailSettings.Password);

            MailMessage mailMessage = new MailMessage();
            mailMessage.From = new MailAddress(mailSettings.Username);
            mailMessage.To.Add(reciever);
            mailMessage.Body = body;
            mailMessage.Subject = subject;
            client.Send(mailMessage);
            return 1;
        }
    }
}
