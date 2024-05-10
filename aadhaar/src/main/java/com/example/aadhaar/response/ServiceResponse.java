package com.example.aadhaar.response;

public class ServiceResponse {
    private String message;

    public ServiceResponse(){}

    public ServiceResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
