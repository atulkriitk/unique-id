package com.example.aadhaar.request;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateAdhaarRequest {
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String firstName;
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String lastName;
    @NotNull
    @NotBlank
    private String mobile;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
