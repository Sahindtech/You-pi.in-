package com.youpi.youpi.service;

import com.youpi.youpi.entity.RechargeDetails;
import com.youpi.youpi.repository.RechargeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RechargeDetailsService {

    @Autowired
    private RechargeDetailsRepository repository;

    // Create
    public RechargeDetails createDetail(RechargeDetails detail) {
        return repository.save(detail);
    }

    // Read all
    public List<RechargeDetails> getAllDetails() {
        return repository.findAll();
    }

    // Read by ID
    public Optional<RechargeDetails> getDetailById(Long id) {
        return repository.findById(id);
    }

    // Update
    public RechargeDetails updatePaymentDetail(Long id, RechargeDetails updatedDetail) {
        return repository.findById(id).map(existing -> {
            existing.setPaymentMethod(updatedDetail.getPaymentMethod());
            existing.setPaymentReferenceId(updatedDetail.getPaymentReferenceId());
            existing.setEmi(updatedDetail.isEmi());
            existing.setEmiPlan(updatedDetail.getEmiPlan());
            existing.setCommissionAmount(updatedDetail.getCommissionAmount());
            existing.setRefunded(updatedDetail.isRefunded());
            existing.setRefundId(updatedDetail.getRefundId());
            existing.setIpAddress(updatedDetail.getIpAddress());
            existing.setDeviceInfo(updatedDetail.getDeviceInfo());
            existing.setRemarks(updatedDetail.getRemarks());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Payment detail not found with id " + id));
    }

    // Delete
    public void deletePaymentDetail(Long id) {
        repository.deleteById(id);
    }

    // ✅ NEW: Callback Process karne ka Logic
    public void processCallback(String orderId, String status, String operatorId) {
        // 1. Order ID (txid) se recharge dhoondo
        RechargeDetails recharge = repository.findByPaymentReferenceId(orderId)
                .orElseThrow(() -> new RuntimeException("Order ID not found: " + orderId));

        // 2. Status Update karo
        if ("Success".equalsIgnoreCase(status)) {
            recharge.setStatus(RechargeDetails.RechargeStatus.SUCCESS);
        } else if ("Failure".equalsIgnoreCase(status)) {
            recharge.setStatus(RechargeDetails.RechargeStatus.FAILED);
        } else {
            System.out.println("Unknown Status Received: " + status);
            recharge.setRemarks("Callback Status: " + status);
        }

        // 3. Operator ID save karo (Reference ke liye)
        recharge.setOperatorRefId(operatorId);

        // 4. Save to DB
        repository.save(recharge);
        System.out.println("✅ Callback Processed for Order: " + orderId);
    }
}