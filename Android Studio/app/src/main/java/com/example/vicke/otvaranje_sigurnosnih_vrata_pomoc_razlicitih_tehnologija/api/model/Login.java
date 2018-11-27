package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model;

public class Login {

    private String usrEmail;
    private String loginPassword;

    public Login(String user, String password)
    {
        this.usrEmail = user;
        this.loginPassword = password;
    }
}
