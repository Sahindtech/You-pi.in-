package com.youpi.youpi.repository;

import com.youpi.youpi.entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminsRepository extends JpaRepository<Admins, Long> {
    Optional<Admins> findByUsername(String username);
}
