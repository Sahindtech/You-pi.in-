package com.youpi.youpi.repository;

import com.youpi.youpi.entity.RechargeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RechargeDetailsRepository extends JpaRepository<RechargeDetails, Long> {

    // ✅ Callback ke liye: Order ID se transaction dhoondhne ka method
    Optional<RechargeDetails> findByPaymentReferenceId(String paymentReferenceId);
}