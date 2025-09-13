package com.youpi.youpi.repository;

import com.youpi.youpi.entity.UsersKYC;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsersKYCRepository extends JpaRepository<UsersKYC, Long> {
    Optional<UsersKYC> findByUserId_Id(Long userId);
}
