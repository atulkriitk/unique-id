package com.example.aadhaar.dao;

import com.example.aadhaar.entity.Adhaar;
import com.example.aadhaar.request.CreateAdhaarServiceRequest;

import java.util.List;

public interface IAdhaarDao {
    void createAdhar(CreateAdhaarServiceRequest request);

    Adhaar getAdhaarByMobile(String mobile);

    void updateAdhaar(Adhaar adhaar);

    Adhaar getAadhaarByAadhaarNUmber(String aadhaarNumber);

    Adhaar getAdhaarWithServicesByMobile(String mobile);
}
