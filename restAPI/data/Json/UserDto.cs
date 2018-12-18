﻿using System;
using System.Collections.Generic;
using System.Text;

namespace data.Json
{
    public class UserDto
    {
        public int UsrId { get; set; }
        public string UsrName { get; set; }
        public string UsrSurname { get; set; }
        public string UsrEmail { get; set; }
        public byte UsrActivity { get; set; }
        public string LoginPassword { get; set; }
        public string PhoneNumber { get; set; }
        public DateTime AccessFrom { get; set; }
        public DateTime AccessTo { get; set; }
        public List<int> Objs { get; set; }
    }
}
