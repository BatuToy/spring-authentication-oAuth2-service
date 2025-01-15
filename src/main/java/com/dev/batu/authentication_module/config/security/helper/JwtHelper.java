package com.dev.batu.authentication_module.config.security.helper;

import com.dev.batu.authentication_module.security.userdetails.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtHelper {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final UserDetailsServiceImpl userDetailsService;

    // Todo: Review this class!
    // Todo: Add a claims in the while generating the JWT!

    public String generateToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(60, ChronoUnit.MINUTES)))
                .setClaims(extractClaims(userName))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        return (Objects.equals(userDetails.getUsername(), extractUserName(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return getTokenExpirationDate(token).before(Date.from(Instant.now()));
    }

    private static Date getTokenExpirationDate(String token){
        return parseToken(token).getExpiration();
    }

    public String extractUserName(String token) {
        return parseToken(token).getSubject();
    }

    private static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    // use parseClaimsJws for signed tokens
                    .getBody();
        } catch (SignatureException | ExpiredJwtException exc){
            log.error("Error occur while parsing the token= {} \n with error= {}", token, exc.getLocalizedMessage());
            throw new AccessDeniedException("Access denied= " +  exc.getLocalizedMessage());
        }
    }

    private Map<String, List<String>> extractClaims(String email){
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        Map<String, List<String>> claims = new HashMap<>();
        claims.put("roles", roles);
        return claims;
    }


}
