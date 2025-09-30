package com.youpi.youpi.repository;

import com.youpi.youpi.entity.UsersNormal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersNormalRepository extends JpaRepository<UsersNormal, Long> {

    // Mobile number se user dhoondhne ke liye
    Optional<UsersNormal> findByMobileNumber(String mobileNumber);

    // Email se user dhoondhne ke liye
    Optional<UsersNormal> findByEmail(String email); // Isko Object se UsersNormal kar diya, yeh aachi practice hai

    // Check karne ke liye ki mobile number pehle se hai ya nahi
    boolean existsByMobileNumber(String mobileNumber);

}