package com.example.aadhaar.services;

import com.example.aadhaar.dao.IAdhaarDao;
import com.example.aadhaar.entity.Adhaar;
import com.example.aadhaar.entity.ServiceSubscription;
import com.example.aadhaar.request.AddServicesRequest;
import com.example.aadhaar.request.CreateAdhaarRequest;
import com.example.aadhaar.request.CreateAdhaarServiceRequest;
import com.example.aadhaar.request.SubscriptionRequest;
import com.example.aadhaar.response.AadhaarDetailsResponse;
import com.example.aadhaar.response.ServiceResponse;
import com.example.aadhaar.response.SubscribedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service("aadhaarService")
public class AdhaarServiceImpl implements IAdhaarService{

    @Autowired
    private IAdhaarDao aadhaarDao;

    @Override
    public ServiceResponse createAdhar(CreateAdhaarRequest request) {
        System.out.println("mobile no from controller "+request.getMobile());
        Adhaar adhaar = aadhaarDao.getAdhaarByMobile(request.getMobile());
        System.out.println(" after getting aadhaar by mobile no " + adhaar);

        if (adhaar == null){
            System.out.println("generating aadhaar number using uuid");
            String aadhaarNumber = generateUniqueId();
            CreateAdhaarServiceRequest serviceRequest = new CreateAdhaarServiceRequest();
            serviceRequest.setAdhaarNumber(aadhaarNumber);
            serviceRequest.setFirstName(request.getFirstName());
            serviceRequest.setLastName(request.getLastName());
            serviceRequest.setMobile(request.getMobile());
            aadhaarDao.createAdhar(serviceRequest);
            return new ServiceResponse("Aadhaar number successfully created");
        }
           else{
            return new ServiceResponse("Aadhaar number already created");
        }
    }

    @Override
    public ServiceResponse updateMobile(String oldMobile, String newMobile) {
        Adhaar adhaar = aadhaarDao.getAdhaarByMobile(oldMobile);
        if (adhaar == null){
            return new ServiceResponse("Aadhaar number not created");
        }
        else {
            adhaar.setMobile(newMobile);
            aadhaarDao.updateAdhaar(adhaar);
            return new ServiceResponse("Aadhaar details is updated successfully");
        }
    }

    @Override
    public ServiceResponse addService(AddServicesRequest request) {
        Adhaar aadhaar = aadhaarDao.getAadhaarByAadhaarNUmber(request.getAdhaar());
        if (aadhaar == null){
            return new ServiceResponse("Aadhaar number not exist");
        }
        List<ServiceSubscription> serviceSubscriptionList = getServiceSubscriptions(request);
        aadhaar.setServiceSubscriptionList(serviceSubscriptionList);
        System.out.println("subscription service "+ serviceSubscriptionList);
        aadhaarDao.updateAdhaar(aadhaar);
        return new ServiceResponse("Aadhaar service has been added");
    }

    @Override
    public AadhaarDetailsResponse getDetails(String mobile) {
        Adhaar adhaar = aadhaarDao.getAdhaarWithServicesByMobile(mobile);
        if(adhaar == null){
            return new AadhaarDetailsResponse(HttpStatus.OK.value(), "No aadhaar number found");
        }
        AadhaarDetailsResponse response = new AadhaarDetailsResponse();
        List<SubscribedService> subscribedServiceList = new ArrayList<>();
        for (ServiceSubscription serviceSubscription : adhaar.getServiceSubscriptionList()){
            SubscribedService subscribedService = new SubscribedService();
            subscribedService.setName(serviceSubscription.getName());
            subscribedService.setDescription(serviceSubscription.getDescription());
            subscribedServiceList.add(subscribedService);
        }
        response.setAadhaar(adhaar.getAdhaarId());
        response.setFirstName(adhaar.getFirstName());
        response.setLastName(adhaar.getLastName());
        response.setSubscribedServiceList(subscribedServiceList);
        return response;
    }

    private List<ServiceSubscription> getServiceSubscriptions(AddServicesRequest request) {
        List<ServiceSubscription> serviceSubscriptionList = new ArrayList<>();
        if(!request.getServices().isEmpty()){
            for (SubscriptionRequest subscriptionRequest : request.getServices()){
                ServiceSubscription serviceSubscription = new ServiceSubscription();
                serviceSubscription.setName(subscriptionRequest.getName());
                serviceSubscription.setDescription(subscriptionRequest.getDescription());
                serviceSubscriptionList.add(serviceSubscription);
            }
        }
        return serviceSubscriptionList;
    }


    private String generateUniqueId() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(12);
        for(int i =0; i<12; i++){
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
