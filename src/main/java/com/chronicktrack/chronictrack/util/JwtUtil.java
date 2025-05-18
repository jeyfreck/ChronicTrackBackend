package com.chronicktrack.chronictrack.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

private final String SECRET = "chronictrack_secret_key";

 private Key getSigningKey() {
        byte[] keyBytes = SECRET.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

public String extractEmail(String token){
return getClaims(token).getSubject();
}

public boolean isTokenValid(String token){
    Date expiration = getClaims(token).getExpiration();
    return expiration.after(new Date());
} 

private Claims getClaims(String token){
    return Jwts.parserBuilder()
    .setSigningKey(getSigningKey()) // Use the Key object instead of a String
    .build()
    .parseClaimsJws(token)
    .getBody();
}
}
