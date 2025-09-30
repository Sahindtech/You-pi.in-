package com.youpi.youpi.service;

import com.youpi.youpi.entity.UsersNormal;
import com.youpi.youpi.repository.UsersNormalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersNormalService {

    @Autowired
    private UsersNormalRepository usersNormalRepository;


    // create a new profile
    public UsersNormal createUserProfile(UsersNormal newUser) {
        // Check if mobileNumber already exists
        if (usersNormalRepository.existsByMobileNumber(newUser.getMobileNumber())) {
            throw new RuntimeException("User already exists");
        }
        return usersNormalRepository.save(newUser);
    }


    // Get user profile by mobile number
    public UsersNormal getUserProfile(String mobileNumber) {
        return usersNormalRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update user profile
    public UsersNormal updateUserProfile(String mobileNumber, UsersNormal updatedData) {
        UsersNormal user = usersNormalRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update only editable fields
        user.setFullName(updatedData.getFullName());
        user.setEmail(updatedData.getEmail());

        return usersNormalRepository.save(user);
    }

    public List<UsersNormal> getAllUsers() {
        return usersNormalRepository.findAll();
    }

    public void deleteUser(Long id) {
        // Check karein ki user exist karta hai ya nahi, agar nahi to exception throw karein
        if (!usersNormalRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        usersNormalRepository.deleteById(id);
    }


    // ✅ User ka status badalne ke liye naya method
    public UsersNormal toggleUserStatus(Long userId) {
        UsersNormal user = usersNormalRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Status ko toggle karein (true se false, false se true)
        user.setActive(!user.isActive());

        return usersNormalRepository.save(user);
    }

    // ✅ Simple Login using Mobile and Password
    public UsersNormal loginUser(String mobileNumber, String password) {
        // Step 1: Mobile number se user ko dhoondein
        UsersNormal user = usersNormalRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found with this mobile number"));

        // Step 2: Password match karein (Abhi ke liye simple check, baad mein hashing use karein)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        // Step 3: Agar sab theek hai, to user ki details return karein
        return user;
    }

    // ... existing loginUser method upar

    // ✅ Check if user exists by mobile number
    public boolean checkIfUserExists(String mobileNumber) {
        return usersNormalRepository.existsByMobileNumber(mobileNumber);
    }

}
