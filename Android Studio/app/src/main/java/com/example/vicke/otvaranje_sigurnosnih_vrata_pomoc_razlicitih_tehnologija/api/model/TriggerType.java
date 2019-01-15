package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model;

public class TriggerType {

    private int triggerTypeId;
    private String triggerName;

    public TriggerType(int triggerTypeId, String triggerName) {
        this.triggerTypeId = triggerTypeId;
        this.triggerName = triggerName;
    }

    public int getTriggerTypeId() {
        return triggerTypeId;
    }

    public void setTriggerTypeId(int triggerTypeId) {
        this.triggerTypeId = triggerTypeId;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }
}
