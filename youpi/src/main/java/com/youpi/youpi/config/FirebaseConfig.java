//package com.youpi.youpi.config;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@Configuration
//public class FirebaseConfig {
//
//    @PostConstruct
//    public void initFirebase() throws IOException {
//        // baad me actual path dalna serviceAccountKey.json ka
//        FileInputStream serviceAccount =
////                with in the ( "" )the credentials will be paste here.
//                new FileInputStream("src/main/resources/firebase/serviceAccountKey.json");
//
//        FirebaseOptions options = FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();
//
//        if (FirebaseApp.getApps().isEmpty()) { // prevent multiple init
//            FirebaseApp.initializeApp(options);
//        }
//    }
//}
