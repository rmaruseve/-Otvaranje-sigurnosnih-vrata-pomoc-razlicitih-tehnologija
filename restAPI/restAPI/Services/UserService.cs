﻿using db.Db;
using restAPI.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace restAPI.Services
{
    public interface IUserService
    {
        AcUser Authenticate(string username, string password);
        IEnumerable<AcUser> GetAll();
        AcUser GetById(int id);
        AcUser Create(AcUser user, string password);
        void Update(AcUser user, string password = null);
        void Delete(int id);
    }

    public class UserService : IUserService
    {
        private mydbContext _context;

        public UserService(mydbContext context)
        {
            _context = context;
        }

        public AcUser Authenticate(string email, string password)
        {
            if (string.IsNullOrEmpty(email) || string.IsNullOrEmpty(password))
                return null;

            var user = _context.AcUser.SingleOrDefault(x => x.UsrEmail == email);

            // check if username exists
            if (user == null)
                return null;

            // check if password is correct
            if (!VerifyPasswordHash(password, System.Text.Encoding.UTF8.GetBytes(user.UsrCryptedPassword), System.Text.Encoding.UTF8.GetBytes(user.UsrPassword)))
                return null;

            // authentication successful
            return user;
        }

        public IEnumerable<AcUser> GetAll()
        {
            return _context.AcUser;
        }

        public AcUser GetById(int id)
        {
            return _context.AcUser.Find(id);
        }

        public AcUser Create(AcUser user, string password)
        {
            // validation
            if (string.IsNullOrWhiteSpace(password))
                throw new AppException("Password is required");

            if (_context.AcUser.Any(x => x.UsrEmail == user.UsrEmail))
                throw new AppException("Username \"" + user.UsrEmail + "\" is already taken");

            byte[] passwordHash, passwordSalt;
            CreatePasswordHash(password, out passwordHash, out passwordSalt);

            user.UsrCryptedPassword = System.Text.Encoding.UTF8.GetString(passwordHash);
            user.UsrPassword = System.Text.Encoding.UTF8.GetString(passwordSalt);

            _context.AcUser.Add(user);
            _context.SaveChanges();

            return user;
        }

        public void Update(AcUser userParam, string password = null)
        {
            var user = _context.AcUser.Find(userParam.UsrId);

            if (user == null)
                throw new AppException("User not found");

            if (userParam.UsrEmail != user.UsrEmail)
            {
                // username has changed so check if the new username is already taken
                if (_context.AcUser.Any(x => x.UsrEmail == userParam.UsrEmail))
                    throw new AppException("Username " + userParam.UsrEmail + " is already taken");
            }

            // update user properties
            user.UsrName = userParam.UsrName;
            user.UsrSurname = userParam.UsrSurname;
            user.UsrEmail = userParam.UsrEmail;

            // update password if it was entered
            if (!string.IsNullOrWhiteSpace(password))
            {
                byte[] passwordHash, passwordSalt;
                CreatePasswordHash(password, out passwordHash, out passwordSalt);

                user.UsrCryptedPassword = System.Text.Encoding.UTF8.GetString(passwordHash);
                user.UsrPassword = System.Text.Encoding.UTF8.GetString(passwordSalt);
            }

            _context.AcUser.Update(user);
            _context.SaveChanges();
        }

        public void Delete(int id)
        {
            var user = _context.AcUser.Find(id);
            if (user != null)
            {
                _context.AcUser.Remove(user);
                _context.SaveChanges();
            }
        }

        // private helper methods

        private static void CreatePasswordHash(string password, out byte[] passwordHash, out byte[] passwordSalt)
        {
            if (password == null) throw new ArgumentNullException("password");
            if (string.IsNullOrWhiteSpace(password)) throw new ArgumentException("Value cannot be empty or whitespace only string.", "password");

            using (var hmac = new System.Security.Cryptography.HMACSHA512())
            {
                passwordSalt = hmac.Key;
                passwordHash = hmac.ComputeHash(System.Text.Encoding.UTF8.GetBytes(password));
            }
        }

        private static bool VerifyPasswordHash(string password, byte[] storedHash, byte[] storedSalt)
        {
            if (password == null) throw new ArgumentNullException("password");
            if (string.IsNullOrWhiteSpace(password)) throw new ArgumentException("Value cannot be empty or whitespace only string.", "password");
            if (storedHash.Length != 64) throw new ArgumentException("Invalid length of password hash (64 bytes expected).", "passwordHash");
            if (storedSalt.Length != 128) throw new ArgumentException("Invalid length of password salt (128 bytes expected).", "passwordHash");

            using (var hmac = new System.Security.Cryptography.HMACSHA512(storedSalt))
            {
                var computedHash = hmac.ComputeHash(System.Text.Encoding.UTF8.GetBytes(password));
                for (int i = 0; i < computedHash.Length; i++)
                {
                    if (computedHash[i] != storedHash[i]) return false;
                }
            }

            return true;
        }
    }
}
