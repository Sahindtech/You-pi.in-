package com.youpi.youpi.controller;

import com.youpi.youpi.dto.UsersKycStatusDTO;
import com.youpi.youpi.entity.UsersKYC;
import com.youpi.youpi.service.UsersKYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-kyc")
public class UsersKYCController {

    @Autowired
    private UsersKYCService usersKYCService;

    // ✅ Is endpoint ko update karein
    @GetMapping
    public List<UsersKycStatusDTO> getAllUsersWithKycStatus() {
        return usersKYCService.getAllUsersWithKycStatus();
    }

    // 🟢 Get KYC by userId
    @GetMapping("/{userId}")
    public UsersKYC getUserKyc(@PathVariable Long userId) {
        return usersKYCService.getKycByUserId(userId);
    }

    // ✅ REQUIRED: Delete KYC by userId
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserKyc(@PathVariable Long userId) {
        usersKYCService.deleteKycByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    // ✅ Admin ke liye: KYC ko verify karne ka endpoint
    @PutMapping("/{userId}/verify")
    public UsersKYC verifyUserKyc(@PathVariable Long userId) {
        return usersKYCService.verifyKyc(userId);
    }

    // ... (Your existing POST and PUT methods are fine)
}