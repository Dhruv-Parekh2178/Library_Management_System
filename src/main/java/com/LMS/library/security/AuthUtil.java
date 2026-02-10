package com.LMS.library.security;

import com.LMS.library.model.MasterUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {

@Value("${jwt.secretKey}")
    private String jwtSecretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

   private SecretKey  getSecretKey(){
       return Keys.hmacShaKeyFor(
               jwtSecretKey.getBytes(StandardCharsets.UTF_8)
       );
   }

    public String generateAccessToken(MasterUser masterUser) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .subject(masterUser.getName())
                .claim("masterUserId", masterUser.getId().toString())
                .claim("masterUserName" , masterUser.getName())
                .issuedAt(new Date(currentTimeMillis))
                .expiration(new Date(currentTimeMillis + jwtExpiration))
                .signWith(getSecretKey())
                .compact();


    }

    public String getUserNameFromToken(String token) {
       Claims claims =  Jwts.parser()
               .verifyWith(getSecretKey())
               .build()
               .parseSignedClaims(token)
               .getPayload();
       return claims.getSubject();
    }

    public Long getExpirationTime() {
        return jwtExpiration;
    }


}
