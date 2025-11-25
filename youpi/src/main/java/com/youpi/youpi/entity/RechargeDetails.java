package com.youpi.youpi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recharge_details")
public class RechargeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ NEW FIELDS (Required for History)
    @Column(nullable = false)
    private String mobileNumber;

    @Column(length = 50)
    private String operator;

    @Column(length = 50)
    private String circle;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private RechargeStatus status = RechargeStatus.PENDING;

    @Column(length = 500)
    private String apiResponse; // Store the raw response from MPlan

    @Column(length = 100)
    private String operatorRefId; // ID from the Operator side

    // --- Existing fields ---
    public enum PaymentMethod {
        WALLET, UPI, CARD, NETBANKING, EMI
    }

    public enum RechargeStatus {
        PENDING, SUCCESS, FAILED, REFUNDED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod = PaymentMethod.UPI;

    @Column(length = 100)
    private String paymentReferenceId;

    @Column(nullable = false)
    private boolean isEmi = false;

    @ManyToOne
    @JoinColumn(name = "emi_plan_id")
    private EmiPlans emiPlan;

    @Column(precision = 10, scale = 2)
    private BigDecimal commissionAmount;

    @Column(nullable = false)
    private boolean refunded = false;

    @Column(length = 100)
    private String refundId;

    @Column(length = 50)
    private String ipAddress;

    @Column(length = 255)
    private String deviceInfo;

    @Column(length = 255)
    private String remarks;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ✅ GETTERS AND SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public String getCircle() { return circle; }
    public void setCircle(String circle) { this.circle = circle; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public RechargeStatus getStatus() { return status; }
    public void setStatus(RechargeStatus status) { this.status = status; }

    public String getApiResponse() { return apiResponse; }
    public void setApiResponse(String apiResponse) { this.apiResponse = apiResponse; }

    public String getOperatorRefId() { return operatorRefId; }
    public void setOperatorRefId(String operatorRefId) { this.operatorRefId = operatorRefId; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentReferenceId() { return paymentReferenceId; }
    public void setPaymentReferenceId(String paymentReferenceId) { this.paymentReferenceId = paymentReferenceId; }

    public boolean isEmi() { return isEmi; }
    public void setEmi(boolean emi) { isEmi = emi; }

    public EmiPlans getEmiPlan() { return emiPlan; }
    public void setEmiPlan(EmiPlans emiPlan) { this.emiPlan = emiPlan; }

    public BigDecimal getCommissionAmount() { return commissionAmount; }
    public void setCommissionAmount(BigDecimal commissionAmount) { this.commissionAmount = commissionAmount; }

    public boolean isRefunded() { return refunded; }
    public void setRefunded(boolean refunded) { this.refunded = refunded; }

    public String getRefundId() { return refundId; }
    public void setRefundId(String refundId) { this.refundId = refundId; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getDeviceInfo() { return deviceInfo; }
    public void setDeviceInfo(String deviceInfo) { this.deviceInfo = deviceInfo; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}