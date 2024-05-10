package com.example.aadhaar.response;

import java.util.List;

public class AadhaarDetailsResponse extends BaseResponse{
    private String aadhaar;
    private String firstName;
    private String lastName;
    private List<SubscribedService> subscribedServiceList;

    public AadhaarDetailsResponse(int code, String message) {
        super(code, message);
    }
    public AadhaarDetailsResponse(){
        super();

    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SubscribedService> getSubscribedServiceList() {
        return subscribedServiceList;
    }

    public void setSubscribedServiceList(List<SubscribedService> subscribedServiceList) {
        this.subscribedServiceList = subscribedServiceList;
    }
}
