package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model;

public class ObjectOpen {

    private String TriggerTypeName;
    private String Value;
    private String ObjectName;

    public ObjectOpen(String value, String objectName)
    {
        this.TriggerTypeName = "App";
        this.Value = value;
        this.ObjectName = objectName;
    }

}
