package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model;

import java.io.Serializable;

public class TriggerList implements Serializable {

    private int triggerId;
    private String triggerValue;
    private int isTriggerActive;

    public int getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(int triggerId) {
        this.triggerId = triggerId;
    }

    public String getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(String triggerValue) {
        this.triggerValue = triggerValue;
    }

    public int getIsTriggerActive() {
        return isTriggerActive;
    }

    public void setIsTriggerActive(int isTriggerActive) {
        this.isTriggerActive = isTriggerActive;
    }
}
