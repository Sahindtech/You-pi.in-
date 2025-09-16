package com.youpi.youpi.service;

import com.youpi.youpi.entity.SmartSaver;
import com.youpi.youpi.repository.SmartSaverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SmartSaverService {

    public final SmartSaverRepository smartSaverRepository;

    public SmartSaverService(SmartSaverRepository smartSaverRepository) {
        this.smartSaverRepository = smartSaverRepository;
    }

    public SmartSaver createEmi(SmartSaver emi) {
        return smartSaverRepository.save(emi);
    }

    public List<SmartSaver> getAllEmis() {
        return smartSaverRepository.findAll();
    }

    public Optional<SmartSaver> getEmiById(Long id) {
        return smartSaverRepository.findById(id);
    }

    public SmartSaver updateEmi(Long id, SmartSaver emiDetails) {
        return smartSaverRepository.findById(id).map(emi -> {
            emi.setPrincipalAmount(emiDetails.getPrincipalAmount());
            emi.setInterestAmount(emiDetails.getInterestAmount());
            emi.setTotalPayable(emiDetails.getTotalPayable());
            emi.setInstallmentAmount(emiDetails.getInstallmentAmount());
            emi.setTenureMonths(emiDetails.getTenureMonths());
            emi.setStartDate(emiDetails.getStartDate());
            emi.setEndDate(emiDetails.getEndDate());
            emi.setNextDueDate(emiDetails.getNextDueDate());
            emi.setStatus(emiDetails.getStatus());
            emi.setPaymentStatus(emiDetails.getPaymentStatus());
            emi.setUpdatedAt(emiDetails.getUpdatedAt());
            return smartSaverRepository.save(emi);
        }).orElseThrow(() -> new RuntimeException("EMI not found with id " + id));
    }

    public void deleteEmi(Long id) {
        smartSaverRepository.deleteById(id);
    }
}
