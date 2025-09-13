package com.youpi.youpi.controller;

import com.youpi.youpi.entity.UsersNormal;
import com.youpi.youpi.service.UsersNormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-normal")
public class UsersNormalController {

    @Autowired
    private UsersNormalService usersNormalService;


    // create a new profile
    @PostMapping("/create")
    public UsersNormal createUser(@RequestBody UsersNormal newUser) {
        return usersNormalService.createUserProfile(newUser.getMobileNumber(), newUser);
    }

    // ✅ Get user profile
    @GetMapping("/profile")
    public UsersNormal getProfile(@RequestParam String mobileNumber) {
        return usersNormalService.getUserProfile(mobileNumber);
    }

    // ✅ Update user profile
    @PutMapping("/profile")
    public UsersNormal updateProfile(@RequestParam String mobileNumber,
                                     @RequestBody UsersNormal updatedData) {
        return usersNormalService.updateUserProfile(mobileNumber, updatedData);
    }
}
