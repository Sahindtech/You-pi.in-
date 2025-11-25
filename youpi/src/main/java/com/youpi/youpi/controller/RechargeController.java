package com.youpi.youpi.controller;

import com.youpi.youpi.dto.MPlanOperatorResponseDTO;
import com.youpi.youpi.entity.RechargeDetails;
import com.youpi.youpi.repository.RechargeDetailsRepository;
import com.youpi.youpi.service.MPlanService;
import com.youpi.youpi.service.A1TopupService;
import com.youpi.youpi.service.RechargeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/recharge-service")
public class RechargeController {

    @Autowired
    private MPlanService mPlanService;

    @Autowired
    private RechargeDetailsRepository rechargeRepository;

    @Autowired
    private A1TopupService a1TopupService;

    @Autowired
    private RechargeDetailsService rechargeDetailsService;

    // 1. Auto-detect Operator (MPlan)
    @GetMapping("/check-operator/{mobile}")
    public MPlanOperatorResponseDTO checkOperator(@PathVariable String mobile) {
        MPlanOperatorResponseDTO response = mPlanService.checkOperator(mobile);

        if (response.getStatus() != null && response.getStatus() == 0) {
            System.out.println("❌ Operator Check Failed: " + response.getRecords());
        }
        return response;
    }

    // 2. Get R-Offers (MPlan)
    @GetMapping("/roffer")
    public String getROffer(@RequestParam String mobile, @RequestParam String operator) {
        return mPlanService.fetchROffer(mobile, operator);
    }

    // 3. Get Normal Plans (MPlan)
    @GetMapping("/plans")
    public String getPlans(@RequestParam String circle, @RequestParam String operator) {
        return mPlanService.fetchSimplePlans(circle, operator);
    }

    // 4. Do Recharge (MPlan - Old Logic, Keep as is)
    @PostMapping("/do-recharge")
    public RechargeDetails doRecharge(@RequestParam String mobile,
                                      @RequestParam String operator,
                                      @RequestParam String amount) {
        RechargeDetails recharge = new RechargeDetails();
        recharge.setMobileNumber(mobile);
        recharge.setOperator(operator);
        recharge.setAmount(new BigDecimal(amount));
        recharge.setStatus(RechargeDetails.RechargeStatus.PENDING);
        recharge.setPaymentMethod(RechargeDetails.PaymentMethod.WALLET);
        recharge = rechargeRepository.save(recharge);

        try {
            String orderId = "RECH_" + recharge.getId();
            String apiResponse = mPlanService.performRecharge(mobile, operator, amount, orderId);
            recharge.setApiResponse(apiResponse);

            if (apiResponse != null && apiResponse.toLowerCase().contains("success")) {
                recharge.setStatus(RechargeDetails.RechargeStatus.SUCCESS);
            } else {
                recharge.setStatus(RechargeDetails.RechargeStatus.FAILED);
            }
        } catch (Exception e) {
            recharge.setStatus(RechargeDetails.RechargeStatus.FAILED);
            recharge.setRemarks("API Error: " + e.getMessage());
        }
        return rechargeRepository.save(recharge);
    }

    // 5. ✅ A1Topup Recharge Endpoint (New)
    @PostMapping("/a1-recharge")
    public String doA1Recharge(@RequestParam String mobile,
                               @RequestParam String amount,
                               @RequestParam String operatorCode,
                               @RequestParam String circleCode) {

        // 1. Unique Order ID generate karo
        String orderId = "ORD" + System.currentTimeMillis();

        // 2. DB me entry karo (PENDING)
        RechargeDetails recharge = new RechargeDetails();
        recharge.setMobileNumber(mobile);
        recharge.setOperator(operatorCode);
        recharge.setCircle(circleCode);
        recharge.setAmount(new BigDecimal(amount));
        recharge.setStatus(RechargeDetails.RechargeStatus.PENDING); // Initial Status
        recharge.setPaymentMethod(RechargeDetails.PaymentMethod.WALLET);
        recharge.setPaymentReferenceId(orderId); // ✅ Save Order ID for Callback

        rechargeRepository.save(recharge);

        // 3. API Call karo
        String response = a1TopupService.performRecharge(mobile, amount, operatorCode, circleCode, orderId);

        // Note: Hum yahan DB update nahi kar rahe kyunki hum Callback ka wait karenge status ke liye
        return response;
    }

    // 6. ✅ Callback API Endpoint
    // A1Topup Server yahan call karega: /api/recharge-service/callback?txid=...&status=...&opid=...
    @GetMapping("/callback")
    public String handleCallback(@RequestParam("txid") String orderId,
                                 @RequestParam("status") String status,
                                 @RequestParam("opid") String operatorId) {

        System.out.println("🔔 Callback Received -> Order: " + orderId + ", Status: " + status);

        try {
            rechargeDetailsService.processCallback(orderId, status, operatorId);
            return "Callback Received & Processed Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error Processing Callback: " + e.getMessage();
        }
    }
}