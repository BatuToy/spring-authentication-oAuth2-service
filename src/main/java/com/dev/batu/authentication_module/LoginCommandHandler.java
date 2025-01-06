package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.config.security.helper.JwtHelper;
import com.dev.batu.authentication_module.domain.attempt.LoginAttempt;
import com.dev.batu.authentication_module.domain.attempt.LoginAttemptId;
import com.dev.batu.authentication_module.dto.login.LoginCommand;
import com.dev.batu.authentication_module.dto.login.LoginResponse;
import com.dev.batu.authentication_module.exception.DomainException;
import com.dev.batu.authentication_module.exception.NotFountException;
import com.dev.batu.authentication_module.mapper.AuthDataMapper;
import com.dev.batu.authentication_module.ports.output.LoginAttemptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginCommandHandler {

    private final LoginCommandHelper loginCommandHelper;

    public LoginResponse login(LoginCommand loginCommand) {
        loginCommandHelper.persisLoginAttempt(loginCommand);
        final String token = JwtHelper.generateToken(loginCommand.getEmail());
        //log.info("User's token created successfully with token expiration date= {}", JwtHelper.getTokenExpirationDate(token));
        return new LoginResponse(token,
                "Login processed successfully for user with email = " + loginCommand.getEmail());
    }


}
