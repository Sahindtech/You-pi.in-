package com.youpi.youpi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_normal")
public class UsersNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User basic info
    @Column(nullable = false, unique = true, length = 15)
    private String mobileNumber;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 200)
    private String fireBaseUUID;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(nullable = false, length = 255) // Length 255 rakhein for hashed passwords
    private String password;

    // ✅ Status fields
    @Column(name = "is_active", nullable = false, columnDefinition = "BIT(1) DEFAULT 1")
    private boolean active = true; // User active hai ya nahi

    // ✅ Naya field yahan add kiya hai
    @Column(name = "is_verified", nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private boolean verified = false; // User verified hai ya nahi (default false)


    // Audit fields
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Constructors
    public UsersNormal() {}

    public UsersNormal(String mobileNumber, String fullName, String email) {
        this.mobileNumber = mobileNumber;
        this.fullName = fullName;
        this.email = email;
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

    // --- Getters & Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // ✅ Naye field ka Getter aur Setter
    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getFireBaseUUID() { return fireBaseUUID; }
    public void setFireBaseUUID(String fireBaseUUID) { this.fireBaseUUID = fireBaseUUID; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}