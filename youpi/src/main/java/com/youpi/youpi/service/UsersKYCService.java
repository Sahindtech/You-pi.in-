package com.youpi.youpi.service;

import com.youpi.youpi.dto.UsersKycStatusDTO;
import com.youpi.youpi.entity.UsersKYC;
import com.youpi.youpi.entity.UsersNormal;
import com.youpi.youpi.repository.UsersKYCRepository;
import com.youpi.youpi.repository.UsersNormalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors; // Yeh import add karna zaroori hai

@Service
public class UsersKYCService {

    @Autowired
    private UsersKYCRepository usersKYCRepository;

    @Autowired
    private UsersNormalRepository usersNormalRepository;

    // ✅ Yeh naya method add karein
    public List<UsersKycStatusDTO> getAllUsersWithKycStatus() {
        // Saare normal users fetch karein
        List<UsersNormal> allUsers = usersNormalRepository.findAll();

        return allUsers.stream().map(user -> {
            // Har user ke liye KYC record check karein
            UsersKYC kyc = usersKYCRepository.findByUserId_Id(user.getId()).orElse(null);

            boolean kycSubmitted = (kyc != null);
            String kycStatus = kycSubmitted ? kyc.getKycStatus().toString() : "NOT_SUBMITTED";
            String name = user.getFullName();

            return new UsersKycStatusDTO(user.getId(), name, user.getMobileNumber(), kycSubmitted, kycStatus);
        }).collect(Collectors.toList());
    }

    // 🔹 Get KYC by userId
    public UsersKYC getKycByUserId(Long userId) {
        return usersKYCRepository.findByUserId_Id(userId)
                .orElseThrow(() -> new RuntimeException("KYC not found for userId: " + userId));
    }

    // ✅ REQUIRED: Delete KYC by userId
    @Transactional
    public void deleteKycByUserId(Long userId) {
        if (!usersKYCRepository.existsByUserId_Id(userId)) {
            throw new RuntimeException("KYC not found for user ID: " + userId);
        }
        usersKYCRepository.deleteByUserId_Id(userId);
    }

    // ✅ Admin use karega isko KYC verify karne ke liye
    @Transactional
    public UsersKYC verifyKyc(Long userId) {
        // Step 1: User ki KYC details nikalein
        UsersKYC kyc = getKycByUserId(userId);

        // Step 2: User ki normal details nikalein
        UsersNormal user = kyc.getUserId();

        // Step 3: KYC status ko VERIFIED set karein
        kyc.setKycStatus(UsersKYC.KycStatus.VERIFIED);
        kyc.setKycVerifiedAt(java.time.LocalDateTime.now()); // verification time bhi set kar dein

        // Step 4: User ka 'isVerified' status true karein
        user.setVerified(true);

        // Step 5: Dono changes ko save karein
        usersNormalRepository.save(user);
        return usersKYCRepository.save(kyc);
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