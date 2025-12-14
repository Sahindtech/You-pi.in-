package com.youpi.youpi.controller;

import com.youpi.youpi.service.RazorpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class RazorpayController {

    @Autowired
    private RazorpayService razorpayService;

    // 1. Create Order API
    @PostMapping("/create-order")
    public String createOrder(@RequestParam int amount) {
        try {
            return razorpayService.createOrder(amount);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating order: " + e.getMessage();
        }
    }

    // 2. Handle Payment Success (Verify Signature)
    @PostMapping("/verify-payment")
    public String verifyPayment(@RequestBody Map<String, String> data) {
        try {
            String orderId = data.get("razorpay_order_id");
            String paymentId = data.get("razorpay_payment_id");
            String signature = data.get("razorpay_signature");

            boolean isValid = razorpayService.verifySignature(orderId, paymentId, signature);

            if (isValid) {
                return "Payment Successful and Verified!";
            } else {
                return "Payment Verification Failed!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}