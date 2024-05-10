package com.example.aadhaar;

import com.example.aadhaar.request.AddServicesRequest;
import com.example.aadhaar.request.CreateAdhaarRequest;
import com.example.aadhaar.request.UpdateMobileRequest;
import com.example.aadhaar.response.AadhaarDetailsResponse;
import com.example.aadhaar.response.BaseResponse;
import com.example.aadhaar.response.ServiceResponse;
import com.example.aadhaar.utils.MobileAndStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.aadhaar.services.IAdhaarService;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/adhaar")
public class AdhaarController {

    @Autowired
    private IAdhaarService adhaarService;

    @RequestMapping("/get")
    public String get(){
    return "hello aadhar";
    }

    @PostMapping("/create")
    public BaseResponse createAdhaar(@Valid @RequestBody CreateAdhaarRequest request, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new BaseResponse(400, "Required fields cannot be empty");
        }
        if(MobileAndStringUtils.removeSpaces(request.getFirstName()).isEmpty() || MobileAndStringUtils.removeSpaces(request.getLastName()).isEmpty()){
            return new BaseResponse(400, "Name field can not be empty");
        }

        if (!MobileAndStringUtils.isValidIndianMobileNumber(request.getMobile())) {
            return new BaseResponse(400, "Aadhaar number can only be created with an Indian mobile number");
        }
        String modifiedMobile = request.getMobile().substring(3);
        request.setMobile(modifiedMobile);
        ServiceResponse response = adhaarService.createAdhar(request);
        return new BaseResponse(HttpStatus.CREATED.value(), response.getMessage());
    }

    @PostMapping("/update/mobile")
    public BaseResponse updateMobile(@RequestBody UpdateMobileRequest request){
        if(request.getOldMobile().isEmpty() || !MobileAndStringUtils.isValidIndianMobileNumber(request.getOldMobile()) || request.getNewMobile().isEmpty() || !MobileAndStringUtils.isValidIndianMobileNumber(request.getNewMobile())){
            return new BaseResponse(400, "Invalid mobile number");
        }
        System.out.println("in update api");
        ServiceResponse response = adhaarService.updateMobile(request.getOldMobile().substring(3), request.getNewMobile().substring(3));
        return new BaseResponse(HttpStatus.OK.value(), response.getMessage());
    }

    @PostMapping("/add/program")
    public BaseResponse addProgram(@RequestBody AddServicesRequest request){
        if(request.getAdhaar().isEmpty()){
            return new BaseResponse(400, "Invalid aadhaar number");
        }
        System.out.println("adding program");
        ServiceResponse response = adhaarService.addService(request);
        return new BaseResponse(HttpStatus.OK.value(), response.getMessage());
    }

    @GetMapping("/get/details")
    public BaseResponse getDeatils(@RequestParam(name = "mobile") String mobile){
        if(mobile.isEmpty()){
            return new BaseResponse(400, "mobile number can not be empty");
        }
        AadhaarDetailsResponse response = adhaarService.getDetails(mobile);
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Success");
        return response;
    }

}
