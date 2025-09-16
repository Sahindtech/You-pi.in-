package com.youpi.youpi.controller;

import com.youpi.youpi.entity.RechargeDetails;
import com.youpi.youpi.service.RechargeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-details")
public class RechargeDetailsController {

    @Autowired
    private RechargeDetailsService service;

    // Create
    @PostMapping("/create")
    public RechargeDetails createPaymentDetail(@RequestBody RechargeDetails detail) {
        return service.createDetail(detail);
    }

    // Get all
    @GetMapping("/all")
    public List<RechargeDetails> getAllPaymentDetails() {
        return service.getAllDetails();
    }

    // Get by ID
    @GetMapping("/{id}")
    public RechargeDetails getPaymentDetailById(@PathVariable Long id) {
        return service.getDetailById(id).orElseThrow(() -> new RuntimeException("Payment detail not found"));
    }

    // Update
    @PutMapping("/update/{id}")
    public RechargeDetails updatePaymentDetail(@PathVariable Long id, @RequestBody RechargeDetails detail) {
        return service.updatePaymentDetail(id, detail);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public String deletePaymentDetail(@PathVariable Long id) {
        service.deletePaymentDetail(id);
        return "Payment detail with ID " + id + " deleted successfully!";
    }
}
