package com.example.local_image_controller_final_project_backend.jwt;

import com.example.local_image_controller_final_project_backend.exceptions.JWTValidationException;
import com.example.local_image_controller_final_project_backend.model.UserModelImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {

    //Key must be long enough - else Exception
    private String jwtSecretKey = "KJD#!2SADASjjw!3213";
    //Expiration time in ms: (1 200 000 ms - 20min)
    private int jwtExpirationTimeMs = 1200000;

    public String generateJwtToken(Authentication authentication) {

        UserModelImpl userPrincipal = (UserModelImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationTimeMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public String getUserNameFromJWT(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isJwtTokenValid(String authToken) throws JWTValidationException {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            throw new JWTValidationException("Cannot parse JWT token");
        }
    }
}
