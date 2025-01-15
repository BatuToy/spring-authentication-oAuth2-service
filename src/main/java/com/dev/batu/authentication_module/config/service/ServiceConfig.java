package com.dev.batu.authentication_module.config.service;

import com.dev.batu.authentication_module.domain.DomainService;
import com.dev.batu.authentication_module.domain.DomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public DomainService domainService(){
        return new DomainServiceImpl();
    }

}
