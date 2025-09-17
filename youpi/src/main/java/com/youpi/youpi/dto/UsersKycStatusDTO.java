package com.youpi.youpi.dto; // Aap apne project ke hisaab se package ka naam rakhein

public class UsersKycStatusDTO {

    private Long userId;
    private String name;
    private String mobileNumber;
    private boolean kycSubmitted;
    private String kycStatus; // PENDING, VERIFIED, REJECTED, ya NOT_SUBMITTED

    // Constructors, Getters, and Setters

    public UsersKycStatusDTO(Long userId, String name, String mobileNumber, boolean kycSubmitted, String kycStatus) {
        this.userId = userId;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.kycSubmitted = kycSubmitted;
        this.kycStatus = kycStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isKycSubmitted() {
        return kycSubmitted;
    }

    public void setKycSubmitted(boolean kycSubmitted) {
        this.kycSubmitted = kycSubmitted;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }
}