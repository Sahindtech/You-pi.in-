package com.youpi.youpi.controller;

import com.youpi.youpi.entity.UsersKYC;
import com.youpi.youpi.service.UsersKYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-kyc")
public class UsersKYCController {

    @Autowired
    private UsersKYCService usersKYCService;

    // 🟢 Get KYC by userId
    @GetMapping("/{userId}")
    public UsersKYC getUserKyc(@PathVariable Long userId) {
        return usersKYCService.getKycByUserId(userId);
    }

    // 🟢 Create KYC (first time)
    @PostMapping("/{userId}")
    public UsersKYC createUserKyc(@PathVariable Long userId, @RequestBody UsersKYC newKyc) {
        return usersKYCService.createKyc(userId, newKyc);
    }

    // 🟢 Update KYC
    @PutMapping("/{userId}")
    public UsersKYC updateUserKyc(@PathVariable Long userId, @RequestBody UsersKYC updatedKyc) {
        return usersKYCService.updateKyc(userId, updatedKyc);
    }
}
