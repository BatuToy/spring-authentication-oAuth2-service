package com.dev.batu.authentication_module.config.security.helper;

import io.jsonwebtoken.*;
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
import java.util.*;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtHelper {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return buildToken(extraClaims != null ? extraClaims : new HashMap<>(), userDetails.getUsername());
    }

    private String buildToken(Map<String, Object> extraClaims, String userName) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userName)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(60, ChronoUnit.MINUTES)))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public <R> R extractClaim(String token, Function<Claims, R> claimResolver){
        Claims claims = parseToken(token);
        if(claims == null){
            throw new MalformedJwtException("Claims are null!");
        }
        return claimResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        return (Objects.equals(userDetails.getUsername(), extractUserName(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return getTokenExpirationDate(token).before(Date.from(Instant.now()));
    }

    private Date getTokenExpirationDate(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException exc){
            log.error("Error occur while parsing the token= {} \n with error= {}", token, exc.getLocalizedMessage());
            throw new AccessDeniedException("Access denied= " +  exc.getLocalizedMessage());
        }
    }


}
