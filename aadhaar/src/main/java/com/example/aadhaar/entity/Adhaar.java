package com.example.aadhaar.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "adhaar")
public class Adhaar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "adhaar_number", unique = true)
    private String adhaarId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile")
    private String mobile;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "adhaar_id")
    private List<ServiceSubscription> serviceSubscriptionList;

    public Adhaar(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdhaarId() {
        return adhaarId;
    }

    public void setAdhaarId(String adhaarId) {
        this.adhaarId = adhaarId;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<ServiceSubscription> getServiceSubscriptionList() {
        return serviceSubscriptionList;
    }

    public void setServiceSubscriptionList(List<ServiceSubscription> serviceSubscriptionList) {
        this.serviceSubscriptionList = serviceSubscriptionList;
    }
}
