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
}
