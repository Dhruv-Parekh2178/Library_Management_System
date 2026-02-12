package com.LMS.library.security;

import com.LMS.library.model.MasterUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static java.security.KeyRep.Type.SECRET;

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

    public String generateAccessToken(Long id,String name) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .subject(name)
                .claim("masterUserId", id.toString())
                .claim("masterUserName" , name)
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

    public boolean validateToken(String token , UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());

    }
}
