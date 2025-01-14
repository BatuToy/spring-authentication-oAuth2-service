package com.dev.batu.authentication_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.dev.batu.authentication_module.dataaccess")
@SpringBootApplication
public class AuthenticationModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationModuleApplication.class, args);
    }

}
