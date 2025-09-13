package com.youpi.youpi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_kyc")
public class UsersKYC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign Key -> users_normal table
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false, unique = true)
    private UsersNormal  userId;

    @Column(length = 20, unique = true, nullable = false)
    private String aadhaarNumber;

    @Column(length = 20, unique = true, nullable = false)
    private String panNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KycStatus kycStatus = KycStatus.PENDING;

    @Column(length = 50)
    private String kycDocumentType;

    @Column(length = 255)
    private String kycDocumentUrl;

    @Column
    private LocalDateTime kycSubmittedAt;

    @Column
    private LocalDateTime kycVerifiedAt;

    @Column(nullable = false)
    private boolean consentSigned = false;

    @Column(length = 255)
    private String incomeProofUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EmploymentType employmentType;

    @Column(precision = 12, scale = 2)
    private BigDecimal monthlyIncome;

    @Column
    private Integer creditScore;

    @Column(length = 500)
    private String verificationNotes;

    @Column(length = 10)
    private String gender;

    @Column(length = 255)
    private String address1;

    @Column(length = 255)
    private String city;

    @Column(length = 50)
    private String state;

    @Column(length = 10)
    private String pincode;

    @Column(length = 255)
    private String dateOfBirth;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    // Enums
    public enum KycStatus {
        PENDING, VERIFIED, REJECTED
    }

    public enum EmploymentType {
        SALARIED, SELF_EMPLOYED, STUDENT, OTHER
    }

    // Constructors
    public UsersKYC() {
    }

    // Lifecycle hooks
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsersNormal getUserId() {
        return userId;
    }

    public void setUserId(UsersNormal userId) {
        this.userId = userId;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public KycStatus getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(KycStatus kycStatus) {
        this.kycStatus = kycStatus;
    }

    public String getKycDocumentType() {
        return kycDocumentType;
    }

    public void setKycDocumentType(String kycDocumentType) {
        this.kycDocumentType = kycDocumentType;
    }

    public String getKycDocumentUrl() {
        return kycDocumentUrl;
    }

    public void setKycDocumentUrl(String kycDocumentUrl) {
        this.kycDocumentUrl = kycDocumentUrl;
    }

    public LocalDateTime getKycSubmittedAt() {
        return kycSubmittedAt;
    }

    public void setKycSubmittedAt(LocalDateTime kycSubmittedAt) {
        this.kycSubmittedAt = kycSubmittedAt;
    }

    public LocalDateTime getKycVerifiedAt() {
        return kycVerifiedAt;
    }

    public void setKycVerifiedAt(LocalDateTime kycVerifiedAt) {
        this.kycVerifiedAt = kycVerifiedAt;
    }

    public boolean isConsentSigned() {
        return consentSigned;
    }

    public void setConsentSigned(boolean consentSigned) {
        this.consentSigned = consentSigned;
    }

    public String getIncomeProofUrl() {
        return incomeProofUrl;
    }

    public void setIncomeProofUrl(String incomeProofUrl) {
        this.incomeProofUrl = incomeProofUrl;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public String getVerificationNotes() {
        return verificationNotes;
    }

    public void setVerificationNotes(String verificationNotes) {
        this.verificationNotes = verificationNotes;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

