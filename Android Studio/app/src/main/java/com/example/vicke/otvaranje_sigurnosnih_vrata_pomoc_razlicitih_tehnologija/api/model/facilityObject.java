package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model;

public class facilityObject {

    private  int id;
    private String name;
    private String objectType;
    private String gpsCoordinate;
    private boolean Available;


    public facilityObject(int id, String name, String objectType, String gpsCoordinate, boolean available) {
        this.id = id;
        this.name = name;
        this.objectType = objectType;
        this.gpsCoordinate = gpsCoordinate;
        this.Available = available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getGPScoordinate() {
        return gpsCoordinate;
    }

    public boolean isAvailable() {
        return Available;
    }
}
