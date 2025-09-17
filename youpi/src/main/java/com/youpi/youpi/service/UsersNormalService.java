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
    public UsersNormal createUserProfile(String mobileNumber, UsersNormal newUser) {
        // Check if mobileNumber already exists
        if (usersNormalRepository.findByMobileNumber(newUser.getMobileNumber()).isPresent()) {
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
        user.setFirstName(updatedData.getFirstName());
        user.setMiddleName(updatedData.getMiddleName());
        user.setLastName(updatedData.getLastName());
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
}
