package com.youpi.youpi.service;

import com.youpi.youpi.entity.EmiPlans;
import com.youpi.youpi.repository.EmiPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmiPlansService {

    @Autowired
    private EmiPlansRepository emiPlansRepository;

    // Create EMI plan
    public EmiPlans createEmiPlan(EmiPlans emiPlan) {
        return emiPlansRepository.save(emiPlan);
    }

    // Get all EMI plans
    public List<EmiPlans> getAllEmiPlans() {
        return emiPlansRepository.findAll();
    }

    // Get EMI plan by id
    public EmiPlans getEmiPlanById(Long id) {
        return emiPlansRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EMI Plan not found with id: " + id));
    }

    // Update EMI plan
    public EmiPlans updateEmiPlan(Long id, EmiPlans updatedPlan) {
        EmiPlans existingPlan = getEmiPlanById(id);

        existingPlan.setMinAmount(updatedPlan.getMinAmount());
        existingPlan.setMaxAmount(updatedPlan.getMaxAmount());
        existingPlan.setDuration(updatedPlan.getDuration());
        existingPlan.setGracePeriodDays(updatedPlan.getGracePeriodDays());
        existingPlan.setInterestRate(updatedPlan.getInterestRate());
        existingPlan.setPenaltyRate(updatedPlan.getPenaltyRate());
        existingPlan.setProvider(updatedPlan.getProvider());
        existingPlan.setAutoDebitAllowed(updatedPlan.getAutoDebitAllowed());
        existingPlan.setProcessingFee(updatedPlan.getProcessingFee());
        existingPlan.setIsActive(updatedPlan.getIsActive());
        existingPlan.setDescription(updatedPlan.getDescription());
        existingPlan.setPlanCode(updatedPlan.getPlanCode());
        existingPlan.setUpdatedAt(updatedPlan.getUpdatedAt());

        return emiPlansRepository.save(existingPlan);
    }

    // Delete EMI plan
    public void deleteEmiPlan(Long id) {
        emiPlansRepository.deleteById(id);
    }
}
