package com.fsse2305.fsse2305_project_backend.utility;

import com.fsse2305.fsse2305_project_backend.data.user.domainObject.FirebaseUserData;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUserData(JwtAuthenticationToken jwtToken){
        return new FirebaseUserData(jwtToken);
    }

    // Overload method to support idToken as a string
    public static FirebaseUserData getFirebaseUserDataFromIdToken(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();

            return new FirebaseUserData(uid, email);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            throw new RuntimeException("Firebase Auth Exception occurred: " + e.getMessage(), e);
        }
    }

}
