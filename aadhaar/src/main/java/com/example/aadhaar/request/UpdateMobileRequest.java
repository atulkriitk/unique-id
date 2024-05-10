package com.example.aadhaar.request;

public class UpdateMobileRequest {
    private String oldMobile;

    private String newMobile;

    public String getOldMobile() {
        return oldMobile;
    }

    public void setOldMobile(String mobile) {
        this.oldMobile = mobile;
    }

    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }
}
