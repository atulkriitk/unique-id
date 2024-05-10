package com.example.aadhaar.services;

import com.example.aadhaar.request.AddServicesRequest;
import com.example.aadhaar.request.CreateAdhaarRequest;
import com.example.aadhaar.response.AadhaarDetailsResponse;
import com.example.aadhaar.response.ServiceResponse;

public interface IAdhaarService {

    ServiceResponse createAdhar(CreateAdhaarRequest request);

    ServiceResponse updateMobile(String oldMobile, String newMobile);

    ServiceResponse addService(AddServicesRequest request);

    AadhaarDetailsResponse getDetails(String mobile);
}
