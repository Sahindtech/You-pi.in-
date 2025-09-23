//package com.youpi.youpi.service;
//
//import com.google.firebase.auth.ActionCodeSettings;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.FirebaseToken;
//import com.youpi.youpi.dto.RegistrationRequest;
//import com.youpi.youpi.entity.UsersNormal;
//import com.youpi.youpi.repository.UsersNormalRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UsersNormalRepository usersNormalRepository;
//
//    private static final Map<String, UsersNormal> pendingUsers = new ConcurrentHashMap<>();
//
//    public String startRegistration(String idToken, RegistrationRequest userData) throws FirebaseAuthException {
//        // Step 1: Frontend se aaye token ko verify karein
//        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
//
//        // ✅ FIX: Phone number ko is tarah se nikalein
//        String phoneNumber = (String) decodedToken.getClaims().get("phone_number");
//
//        String uid = decodedToken.getUid();
//
//        // Step 2: Check karein ki phone ya email pehle se registered toh nahi hai
//        if (usersNormalRepository.findByMobileNumber(phoneNumber).isPresent()) {
//            throw new RuntimeException("A user with this phone number already exists.");
//        }
//        if (usersNormalRepository.findByEmail(userData.getEmail()).isPresent()) {
//            throw new RuntimeException("A user with this email address already exists.");
//        }
//
//        // Step 3: User ki details ko temporary map mein save karein
//        UsersNormal pendingUser = new UsersNormal();
//        pendingUser.setMobileNumber(phoneNumber);
//        pendingUser.setFirstName(userData.getFirstName());
//        pendingUser.setLastName(userData.getLastName());
//        pendingUser.setEmail(userData.getEmail());
//        pendingUsers.put(uid, pendingUser);
//
//        // Step 4: Email verification link ke liye settings banayein
//        ActionCodeSettings actionCodeSettings = ActionCodeSettings.builder()
//                .setUrl("http://localhost:8080/api/auth/register/finalize?uid=" + uid)
//                .setHandleCodeInApp(true)
//                .build();
//
//        // Step 5: Firebase se email link generate karein
//        String emailLink = FirebaseAuth.getInstance()
//                .generateSignInWithEmailLink(userData.getEmail(), actionCodeSettings);
//
//        // Step 6: Link ko console par print karein (Testing ke liye)
//        System.out.println("--- Firebase Verification Link ---");
//        System.out.println("Copy and open this link in a browser to verify email for user UID: " + uid);
//        System.out.println(emailLink);
//        System.out.println("---------------------------------");
//
//        return "Verification link has been generated. For testing, please check your Spring Boot console for the link.";
//    }
//
//    public UsersNormal finalizeRegistration(String uid) {
//        UsersNormal userToRegister = pendingUsers.get(uid);
//
//        if (userToRegister == null) {
//            throw new RuntimeException("Registration session expired or is invalid. Please start the registration process again.");
//        }
//
//        UsersNormal savedUser = usersNormalRepository.save(userToRegister);
//        pendingUsers.remove(uid);
//        return savedUser;
//    }
//}