package com.youpi.youpi.repository;

import com.youpi.youpi.entity.RechargeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargeDetailsRepository extends JpaRepository<RechargeDetails, Long> {
    // Agar future me custom query likhni ho to yaha add karna
}
