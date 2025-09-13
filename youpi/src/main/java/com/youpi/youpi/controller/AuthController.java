//package com.youpi.youpi.controller;
//
//import com.youpi.youpi.service.UsersNormalService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.FirebaseToken;
//
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private UsersNormalService usersNormalService;
//
//    // Step 1: Verify Firebase ID Token (instead of OTP)
//    @PostMapping("/verify-firebase")
//    public String verifyFirebase(@RequestParam String idToken, HttpSession session) {
//        try {
//            // Firebase se token verify karo
//            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
//
//// Phone number fetch from claims
//            String mobileNumber = (String) decodedToken.getClaims().get("phone_number");
//            if (mobileNumber == null) {
//                return "Invalid Firebase token: Phone number missing!";
//            }
//
//            // User DB me check karo
//            if(UsersNormalService.getUserIfExists(mobileNumber) == null){
//                UsersNormalService.createUser(mobileNumber);
//            }
//
//            // Session me save
//            session.setAttribute("mobileNumber", mobileNumber);
//            return "Firebase verified! User logged in.";
//
//        } catch (FirebaseAuthException e) {
//            return "Firebase verification failed: " + e.getMessage();
//        }
//    }
//}
