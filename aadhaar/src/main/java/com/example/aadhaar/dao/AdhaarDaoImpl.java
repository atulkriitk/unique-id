package com.example.aadhaar.dao;

import com.example.aadhaar.entity.Adhaar;
import com.example.aadhaar.request.CreateAdhaarServiceRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdhaarDaoImpl implements IAdhaarDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createAdhar(CreateAdhaarServiceRequest request) {
        Adhaar adhaar = new Adhaar();
        adhaar.setAdhaarId(request.getAdhaarNumber());
        adhaar.setFirstName(request.getFirstName());
        adhaar.setLastName(request.getLastName());
        adhaar.setMobile(request.getMobile());
        entityManager.persist(adhaar);
    }

    @Override
    public Adhaar getAdhaarByMobile(String mobile) {
        try {
            TypedQuery<Adhaar> query = entityManager.createQuery("from Adhaar where mobile =:mobile", Adhaar.class);
            query.setParameter("mobile", mobile);
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }

    }

    @Override
    @Transactional
    public void updateAdhaar(Adhaar adhaar) {
        entityManager.merge(adhaar);
    }

    @Override
    public Adhaar getAadhaarByAadhaarNUmber(String aadhaarNumber) {
        System.out.println("getting adhaar by mobie "+ aadhaarNumber);
        try {
            TypedQuery<Adhaar> query = entityManager.createQuery("SELECT a FROM Adhaar a WHERE a.adhaarId = :aadhaarNumber", Adhaar.class);
            query.setParameter("aadhaarNumber", aadhaarNumber);
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Adhaar getAdhaarWithServicesByMobile(String mobile) {
        System.out.println("Getting Aadhaar details with services by mobile number: " + mobile);
        try {
            TypedQuery<Adhaar> query = entityManager.createQuery("SELECT a FROM Adhaar a JOIN FETCH a.serviceSubscriptionList s WHERE a.mobile = :mobile", Adhaar.class);
            query.setParameter("mobile", mobile);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No Aadhaar found for mobile number: " + mobile);
            return null;
        }
    }
}
