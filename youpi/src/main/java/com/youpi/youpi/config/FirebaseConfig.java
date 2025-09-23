//package com.youpi.youpi.config; // Aapka package name
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//
//import jakarta.annotation.PostConstruct;
//import java.io.IOException;
//import java.io.InputStream;
//
//@Service
//public class FirebaseConfig {
//
//    // Yeh value aapke application.properties se aayegi
//    @Value("${app.firebase-configuration-file}")
//    private String firebaseConfigPath;
//
//    @PostConstruct
//    public void initialize() {
//        try {
//            // serviceAccountKey.json file ko resources folder se load karein
//            InputStream serviceAccount = new ClassPathResource(firebaseConfigPath).getInputStream();
//
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//
//            // Check karein ki app pehle se initialize toh nahi hai
//            if (FirebaseApp.getApps().isEmpty()) {
//                FirebaseApp.initializeApp(options);
//                System.out.println("Firebase application has been initialized");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}