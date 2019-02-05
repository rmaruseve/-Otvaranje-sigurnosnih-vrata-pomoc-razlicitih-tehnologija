package com.example.core.api.model;

public class Login {

    private String UsrEmail;
    private String LoginPassword;

    public Login(String user, String password)
    {
        this.UsrEmail = user;
        this.LoginPassword = password;
    }
}
