package com.dev.batu.authentication_module.config.service;

import com.dev.batu.authentication_module.domain.DomainService;
import com.dev.batu.authentication_module.domain.DomainServiceImpl;
import com.dev.batu.authentication_module.ports.output.UserRepository;
import com.dev.batu.authentication_module.security.userdetails.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfig {

    @Bean
    public DomainService domainService(){
        return new DomainServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
