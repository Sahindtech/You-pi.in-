package com.youpi.youpi.repository;

import com.youpi.youpi.entity.UsersKYC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersKYCRepository extends JpaRepository<UsersKYC, Long> {

    // Find KYC by the user's ID (the foreign key)
    Optional<UsersKYC> findByUserId_Id(Long userId);

    // ✅ REQUIRED: Method to check if a KYC record exists for a user ID
    boolean existsByUserId_Id(Long userId);

    // ✅ REQUIRED: Method to delete a KYC record by the user ID
    void deleteByUserId_Id(Long userId);
}