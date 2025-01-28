package com.dev.batu.authentication_module.rest;

import com.dev.batu.authentication_module.dto.login.TrackLoginAttemptsResponse;
import com.dev.batu.authentication_module.ports.input.AuthenticationApplicationService;
import com.dev.batu.authentication_module.dto.login.LoginCommand;
import com.dev.batu.authentication_module.dto.login.LoginResponse;
import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.dto.register.RegisterResponse;
import com.dev.batu.authentication_module.dto.response.AppResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationApplicationService applicationService;

    @PostMapping(value = "/register")
    public AppResponse<RegisterResponse> register(@RequestBody RegisterCommand registerCommand){
        log.info("Register started for user with user name= {}", registerCommand.getEmail());
        RegisterResponse response = applicationService.register(registerCommand);
        log.info("Registered successfully for user with email= {}", registerCommand.getEmail());
        return new AppResponse<>(
                response,
                HttpStatus.OK,
                "Registered successfully in the application!"
        );
    }

    @PostMapping(value = "/login")
    public AppResponse<LoginResponse> login(@RequestBody LoginCommand loginCommand){
        log.info("Login started for user with email= {}", loginCommand.getEmail());
        LoginResponse response = applicationService.login(loginCommand);
        log.info("Logged in successfully user with token= {}", response.getToken());
        return new AppResponse<>(
                response,
                HttpStatus.ACCEPTED,
                "Logged in successfully in the application!"
        );
    }

    @GetMapping(value = "/loginAttempts")
    @PreAuthorize("hasRole('ADMIN')")
    public AppResponse<TrackLoginAttemptsResponse> loginAttempts(){
        log.info("Login attempts starting to listing!");
        TrackLoginAttemptsResponse response = applicationService.trackLoginAttempts();
        return new AppResponse<>(
                response,
                HttpStatus.OK,
                "Login attempts listed successfully!"
        );
    }
}
