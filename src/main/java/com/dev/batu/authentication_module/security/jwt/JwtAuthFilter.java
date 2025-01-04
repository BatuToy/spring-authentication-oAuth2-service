package com.dev.batu.authentication_module.security.jwt;

import com.dev.batu.authentication_module.config.security.helper.JwtHelper;
import com.dev.batu.authentication_module.security.userdetails.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.Serial;
import java.nio.file.AccessDeniedException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {


    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String userName = null;
            if(authHeader != null && authHeader.startsWith("Bearer ")){
                token = authHeader.substring(7);
                userName = jwtHelper.extractUserName(userName);
                log.info("Request object have \n Token= {} \n userName= {}", token, userName);
            }
            // Each chain represents the pass button. So if we don't have any token
            // which in the signup and log in request end user doesn't have any we pass the filter go for next filter!
            if(token == null){
                log.info("Token yet not given to user!");
                filterChain.doFilter(request, response);
                return;
            }
            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
                if(jwtHelper.isTokenValid(token, userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                    authenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request)); // new WebAuthenticationDetails(request);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.info("Authentication object persisted successfully in to SecurityContextHolder \n with= {}", authenticationToken.getCredentials());
                }
            }
            filterChain.doFilter(request, response);
        } catch (AccessDeniedException exc){
            log.error("Access denied= {}", exc.getLocalizedMessage());
            throw new AccessDeniedException("Access denied= " + exc.getLocalizedMessage());
        }
    }
}
