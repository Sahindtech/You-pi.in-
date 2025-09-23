//package com.youpi.youpi.controller;
//
//import com.youpi.youpi.dto.RegistrationRequest; // Neeche diye gaye DTO ko import karein
//import com.youpi.youpi.entity.UsersNormal;
//import com.youpi.youpi.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    /**
//     * Step 1 of Registration: Verify phone token and send email link.
//     * Frontend will call this after phone OTP verification.
//     */
//    @PostMapping("/register/start")
//    public ResponseEntity<String> startRegistration(@RequestBody RegistrationRequest request) {
//        try {
//            String responseMessage = authService.startRegistration(request.getIdToken(), request);
//            return ResponseEntity.ok(responseMessage);
//        } catch (Exception e) {
//            // RuntimeException ko handle karein (jaise user already exists)
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    /**
//     * Step 2 of Registration: Finalize after user clicks the email link.
//     * This endpoint is the URL you provide in the ActionCodeSettings.
//     */
//    @GetMapping("/register/finalize")
//    public String finalizeRegistration(@RequestParam String uid) {
//        try {
//            authService.finalizeRegistration(uid);
//            // User ko ek success page dikhayein
//            return "<h1>Registration Successful!</h1><p>Your email has been verified and your account is created. You can now close this window.</p>";
//        } catch (Exception e) {
//            // Error page dikhayein
//            return "<h1>Registration Failed!</h1><p>" + e.getMessage() + "</p>";
//        }
//    }
//}