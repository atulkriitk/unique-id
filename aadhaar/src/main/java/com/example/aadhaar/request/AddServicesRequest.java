package com.example.aadhaar.request;

import java.util.List;

public class AddServicesRequest {
    private String adhaar;
    private List<SubscriptionRequest> services;

    public String getAdhaar() {
        return adhaar;
    }

    public void setAdhaar(String adhaar) {
        this.adhaar = adhaar;
    }

    public List<SubscriptionRequest> getServices() {
        return services;
    }

    public void setServices(List<SubscriptionRequest> services) {
        this.services = services;
    }
}
