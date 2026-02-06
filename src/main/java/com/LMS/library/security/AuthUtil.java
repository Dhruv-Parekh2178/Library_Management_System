package com.LMS.library.security;

import com.LMS.library.model.MasterUser;
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

   private SecretKey  getSecretKey(){
       return Keys.hmacShaKeyFor(
               jwtSecretKey.getBytes(StandardCharsets.UTF_8)
       );
   }

    public String generateAccessToken(MasterUser masterUser) {
        return Jwts.builder()
                .subject(masterUser.getName())
                .claim("masterUserId", masterUser.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 1000*60*10))
                .signWith(getSecretKey())
                .compact();


    }
}
