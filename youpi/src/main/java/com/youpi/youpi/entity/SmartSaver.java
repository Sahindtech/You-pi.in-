package com.youpi.youpi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "smart_saver")
public class SmartSaver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Foreign Key -> users(id)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersNormal user;

    // 🔗 Foreign Key -> recharge_transactions(id)
    @ManyToOne
    @JoinColumn(name = "recharge_id", nullable = false)
    private RechargeDetails recharge;

    // 🔗 Foreign Key -> emi_plans(id)
    @ManyToOne
    @JoinColumn(name = "emi_plan_id", nullable = false)
    private EmiPlans emiPlan;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal principalAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal interestAmount = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPayable;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal installmentAmount;

    @Column(nullable = false)
    private Integer tenureMonths;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private LocalDate nextDueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(nullable = false)
    private String provider = "SmartSaver";

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum Status {
        ACTIVE, COMPLETED, DEFAULTED, CANCELLED
    }

    public enum PaymentStatus {
        PENDING, PAID, OVERDUE
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UsersNormal getUser() { return user; }
    public void setUser(UsersNormal user) { this.user = user; }

    public RechargeDetails getRecharge() { return recharge; }
    public void setRecharge(RechargeDetails recharge) { this.recharge = recharge; }

    public EmiPlans getEmiPlan() { return emiPlan; }
    public void setEmiPlan(EmiPlans emiPlan) { this.emiPlan = emiPlan; }

    public BigDecimal getPrincipalAmount() { return principalAmount; }
    public void setPrincipalAmount(BigDecimal principalAmount) { this.principalAmount = principalAmount; }

    public BigDecimal getInterestAmount() { return interestAmount; }
    public void setInterestAmount(BigDecimal interestAmount) { this.interestAmount = interestAmount; }

    public BigDecimal getTotalPayable() { return totalPayable; }
    public void setTotalPayable(BigDecimal totalPayable) { this.totalPayable = totalPayable; }

    public BigDecimal getInstallmentAmount() { return installmentAmount; }
    public void setInstallmentAmount(BigDecimal installmentAmount) { this.installmentAmount = installmentAmount; }

    public Integer getTenureMonths() { return tenureMonths; }
    public void setTenureMonths(Integer tenureMonths) { this.tenureMonths = tenureMonths; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public LocalDate getNextDueDate() { return nextDueDate; }
    public void setNextDueDate(LocalDate nextDueDate) { this.nextDueDate = nextDueDate; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
