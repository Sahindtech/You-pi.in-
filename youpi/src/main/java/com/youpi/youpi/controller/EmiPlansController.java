package com.youpi.youpi.controller;

import com.youpi.youpi.entity.EmiPlans;
import com.youpi.youpi.service.EmiPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emi-plans")
public class EmiPlansController {

    @Autowired
    private EmiPlansService emiPlansService;

    // Create EMI plan
    @PostMapping("/create")
    public EmiPlans createEmiPlan(@RequestBody EmiPlans emiPlan) {
        return emiPlansService.createEmiPlan(emiPlan);
    }

    // Get all EMI plans
    @GetMapping("/all")
    public List<EmiPlans> getAllEmiPlans() {
        return emiPlansService.getAllEmiPlans();
    }

    // Get EMI plan by id
    @GetMapping("/{id}")
    public EmiPlans getEmiPlanById(@PathVariable Long id) {
        return emiPlansService.getEmiPlanById(id);
    }

    // Update EMI plan
    @PutMapping("/{id}")
    public EmiPlans updateEmiPlan(@PathVariable Long id, @RequestBody EmiPlans emiPlan) {
        return emiPlansService.updateEmiPlan(id, emiPlan);
    }

    // Delete EMI plan
    @DeleteMapping("/{id}")
    public String deleteEmiPlan(@PathVariable Long id) {
        emiPlansService.deleteEmiPlan(id);
        return "EMI Plan with id " + id + " deleted successfully.";
    }
}
