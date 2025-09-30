package com.youpi.youpi.controller;

import com.youpi.youpi.entity.UsersNormal;
import com.youpi.youpi.service.UsersNormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Yeh import add karein

@RestController
// ✅ Step 1: URL ko "users-normal" (plural) kiya gaya hai
@RequestMapping("/api/users-normal")
// Note: Aapka CorsConfig file theek hai, isliye yahan @CrossOrigin ki zaroorat nahi hai
public class UsersNormalController {

    @Autowired
    private UsersNormalService usersNormalService;

    // ✅ Step 2: Sabhi users ko fetch karne ke liye naya endpoint
    @GetMapping
    public List<UsersNormal> getAllUsers() {
        return usersNormalService.getAllUsers();
    }

    // create a new profile
    @PostMapping("/create")
    public UsersNormal createUser(@RequestBody UsersNormal newUser) {
        return usersNormalService.createUserProfile(newUser);
    }

    // Get user profile
    @GetMapping("/profile")
    public UsersNormal getProfile(@RequestParam String mobileNumber) {
        return usersNormalService.getUserProfile(mobileNumber);
    }

    // Update user profile
    @PutMapping("/profile")
    public UsersNormal updateProfile(@RequestParam String mobileNumber,
                                     @RequestBody UsersNormal updatedData) {
        return usersNormalService.updateUserProfile(mobileNumber, updatedData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersNormalService.deleteUser(id);
        // Success par 204 No Content response bhejein
        return ResponseEntity.noContent().build();
    }

    // ✅ User ka status toggle karne ke liye naya endpoint
    @PutMapping("/{userId}/toggle-status")
    public UsersNormal toggleUserStatus(@PathVariable Long userId) {
        return usersNormalService.toggleUserStatus(userId);
    }


    // ✅ Login endpoint
//    @PostMapping("/login")
//    public UsersNormal login(@RequestBody com.youpi.youpi.dto.LoginRequestDTO loginRequest) {
//        return usersNormalService.loginUser(loginRequest.getMobileNumber(), loginRequest.getPassword());
//    }

    // ... existing login endpoint upar

    // ✅ Check if a user exists by mobile number
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkUserExists(@RequestParam String mobileNumber) {
        boolean exists = usersNormalService.checkIfUserExists(mobileNumber);
        return ResponseEntity.ok(exists);
    }

}