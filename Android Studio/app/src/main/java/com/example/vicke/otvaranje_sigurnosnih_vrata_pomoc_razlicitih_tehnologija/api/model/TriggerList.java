package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model;

import java.util.ArrayList;

public class TriggerList {

    private String triggerName;
    private String triggerValue;
    private boolean isTriggerActive;

    public TriggerList()
    {

    }

    public TriggerList(String triggerName, String triggerValue, boolean isTriggerActive)
    {
        this.triggerName = triggerName;
        this.triggerValue = triggerValue;
        this.isTriggerActive = isTriggerActive;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(String triggerValue) {
        this.triggerValue = triggerValue;
    }

    public boolean isTriggerActive() {
        return isTriggerActive;
    }

    public void setTriggerActive(boolean triggerActive) {
        isTriggerActive = triggerActive;
    }
}
