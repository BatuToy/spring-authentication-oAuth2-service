package com.dev.batu.authentication_module.config.security.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtHelper {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(60, ChronoUnit.MINUTES)))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String userName = extractUserName(token);
        return (Objects.equals(userDetails.getUsername(), userName) && isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        Date expiration = parseToken(token).getExpiration();
        return expiration.before(Date.from(Instant.now()));
    }

    public String extractUserName(String token) {
        return parseToken(token).getSubject();
    }

    private static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJwt(token)
                    .getBody();
        } catch (SignatureException | ExpiredJwtException exc){
            log.error("Error occur while parsing the token= {} \n with error= {}", token.substring(7), exc.getLocalizedMessage());
            throw new AccessDeniedException("Access denied= " +  exc.getLocalizedMessage());
        }
    }


}