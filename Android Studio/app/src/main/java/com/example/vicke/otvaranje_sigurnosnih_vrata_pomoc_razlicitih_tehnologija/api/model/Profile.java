package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model;

public class Profile {

    private int profileId;
    private String profileName;
    private int profileActivity;

    public Profile()
    {

    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getProfileActivity() {
        return profileActivity;
    }

    public void setProfileActivity(int profileActivity) {
        this.profileActivity = profileActivity;
    }

    @Override
    public String toString() {
        return profileName;
    }
}
