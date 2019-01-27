package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model;

public class facilityObject {

    private  int objId;
    private String objName;
    private byte objOpen;
    private byte objAuto;
    private byte objActivity;
    private String objGps;
    private String objAction;
    private String objObtTypeId;

    public facilityObject() {

        //int ObjId, String ObjName, byte ObjOpen, byte ObjAuto, byte ObjActivity, String ObjGps, String ObjAction, String ObjObtTypeId

        /*this.objId = ObjId;
        this.objName = ObjName;
        this.objOpen = ObjOpen;
        this.objAuto = ObjAuto;
        this.objActivity = ObjActivity;
        this.objGps = ObjGps;
        this.objAction = ObjAction;
        this.objObtTypeId = ObjObtTypeId;*/
    }

    public int getObjId() {
        return objId;
    }

    public String getObjName() {
        return objName;
    }

    public byte getObjOpen() {
        return objOpen;
    }

    public byte getObjAuto() {
        return objAuto;
    }

    public byte getObjActivity() {
        return objActivity;
    }

    public String getObjGps() {
        return objGps;
    }

    public String getObjAction() {
        return objAction;
    }

    public String getObjObtTypeId() {
        return objObtTypeId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public void setObjOpen(byte objOpen) {
        this.objOpen = objOpen;
    }

    public void setObjAuto(byte objAuto) {
        this.objAuto = objAuto;
    }

    public void setObjActivity(byte objActivity) {
        this.objActivity = objActivity;
    }

    public void setObjGps(String objGps) {
        this.objGps = objGps;
    }

    public void setObjAction(String objAction) {
        this.objAction = objAction;
    }

    public void setObjObtTypeId(String objObtTypeId) {
        this.objObtTypeId = objObtTypeId;
    }

    @Override
    public String toString() {
        return objName;
    }
}
