package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model;

import java.io.Serializable;

public class User implements Serializable {

    private int Id;
    private String Email;
    private String FirstName;
    private String LastName;
    private String Token;

    public int getId() {
        return Id;
    }

    public String getEmail() {
        return Email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getToken() {
        return Token;
    }
}
