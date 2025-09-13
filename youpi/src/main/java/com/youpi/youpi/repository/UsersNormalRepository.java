package com.youpi.youpi.repository;
import com.youpi.youpi.entity.UsersNormal;
import com.youpi.youpi.service.UsersKYCService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersNormalRepository extends JpaRepository<UsersNormal, Long> {
    Optional<UsersNormal> findByMobileNumber(String mobileNumber);

    Optional<Object> findById(UsersKYCService userId);
}