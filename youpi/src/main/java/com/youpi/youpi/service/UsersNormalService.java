package com.youpi.youpi.service;

import com.youpi.youpi.entity.UsersNormal;
import com.youpi.youpi.dto.UpdateProfileRequestDTO;
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

    // ✅ Profile update karne ke liye updated logic
    public UsersNormal updateUserProfile(String mobileNumber, UpdateProfileRequestDTO updatedData) {
        UsersNormal existingUser = usersNormalRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found with mobile number: " + mobileNumber));

        // Puraani details ko nayi details se update karein
        existingUser.setFullName(updatedData.getFullName());
        existingUser.setEmail(updatedData.getEmail());
        existingUser.setGender(updatedData.getGender());
        existingUser.setProfileImageUrl(updatedData.getProfileImageUrl()); // ✅ YEH NAYI LINE ADD HUI HAI

        return usersNormalRepository.save(existingUser);
    }

    // ... existing loginUser method upar

    // ✅ Check if user exists by mobile number
    public boolean checkIfUserExists(String mobileNumber) {
        return usersNormalRepository.existsByMobileNumber(mobileNumber);
    }

}
