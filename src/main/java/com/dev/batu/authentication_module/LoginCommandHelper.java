package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.attempt.LoginAttempt;
import com.dev.batu.authentication_module.domain.attempt.LoginAttemptId;
import com.dev.batu.authentication_module.dto.login.LoginCommand;
import com.dev.batu.authentication_module.exception.DomainException;
import com.dev.batu.authentication_module.ports.output.LoginAttemptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginCommandHelper {

    private final AuthenticationManager authenticationManager;
    private final LoginAttemptCommandHelper loginAttemptCommandHelper;

    public void persisLoginAttempt(LoginCommand loginCommand){
        log.info("Persisting the login attempt saving for user with email= {}", loginCommand.getEmail());
        LoginAttempt loginAttempt = LoginAttempt.builder()
                .loginAttemptId( new LoginAttemptId( UUID.randomUUID()))
                .attemptedAt( ZonedDateTime.now(ZoneId.of("UTC")))
                .isSuccess(true)
                .build();
        checkLogInAttempt(loginCommand, loginAttempt);
        loginAttemptCommandHelper.saveLoginAttempt(loginAttempt);
        log.info("Login attempted successfully with id= {}", loginAttempt.getId().getValue());
    }

    private void checkLogInAttempt(LoginCommand loginCommand, LoginAttempt loginAttempt){
        try{
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginCommand.getEmail(), loginCommand.getRawPassword()));
        } catch (BadCredentialsException exc){
            loginAttempt.setSuccess(false);
            loginAttemptCommandHelper.saveLoginAttempt(loginAttempt);
            log.error(exc.getLocalizedMessage(), exc);
            throw new BadCredentialsException("Authentication for login fails cause of bad credentials \t error= " + exc.getLocalizedMessage());
        }
    }

}
