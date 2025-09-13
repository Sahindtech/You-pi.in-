package com.youpi.youpi.service;

import com.youpi.youpi.entity.UsersKYC;
import com.youpi.youpi.entity.UsersNormal;
import com.youpi.youpi.repository.UsersKYCRepository;
import com.youpi.youpi.repository.UsersNormalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersKYCService {

    @Autowired
    private UsersKYCRepository usersKYCRepository;

    @Autowired
    private UsersNormalRepository usersNormalRepository;

    // 🔹 Get KYC by userId
    public UsersKYC getKycByUserId(Long userId) {
        return usersKYCRepository.findByUserId_Id(userId)
                .orElseThrow(() -> new RuntimeException("KYC not found for userId: " + userId));
    }

    // 🔹 Create new KYC (first time user fills profile)
    public UsersKYC createKyc(Long userId, UsersKYC newKyc) {
        UsersNormal user = usersNormalRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if already exists
        if (usersKYCRepository.findByUserId_Id(userId).isPresent()) {
            throw new RuntimeException("KYC already exists for userId: " + userId);
        }

        newKyc.setUserId(user);  // FK set karo
        return usersKYCRepository.save(newKyc);
    }

    // 🔹 Update existing KYC
    public UsersKYC updateKyc(Long userId, UsersKYC updatedKyc) {
        UsersKYC existingKyc = usersKYCRepository.findByUserId_Id(userId)
                .orElseThrow(() -> new RuntimeException("KYC not found for userId: " + userId));

        // Fields update karo (sirf allowed fields)
        existingKyc.setAadhaarNumber(updatedKyc.getAadhaarNumber());
        existingKyc.setPanNumber(updatedKyc.getPanNumber());
        existingKyc.setKycDocumentType(updatedKyc.getKycDocumentType());
        existingKyc.setKycDocumentUrl(updatedKyc.getKycDocumentUrl());
        existingKyc.setConsentSigned(updatedKyc.isConsentSigned());
        existingKyc.setIncomeProofUrl(updatedKyc.getIncomeProofUrl());
        existingKyc.setEmploymentType(updatedKyc.getEmploymentType());
        existingKyc.setMonthlyIncome(updatedKyc.getMonthlyIncome());
        existingKyc.setCreditScore(updatedKyc.getCreditScore());
        existingKyc.setGender(updatedKyc.getGender());
        existingKyc.setAddress1(updatedKyc.getAddress1());
        existingKyc.setCity(updatedKyc.getCity());
        existingKyc.setState(updatedKyc.getState());
        existingKyc.setPincode(updatedKyc.getPincode());
        existingKyc.setDateOfBirth(updatedKyc.getDateOfBirth());

        return usersKYCRepository.save(existingKyc);
    }
}
