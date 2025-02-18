package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.DomainService;
import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.event.loginattempt.UserAuthenticateEvent;
import com.dev.batu.authentication_module.dto.login.LoginCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginCommandHelper {

    private final AuthenticationManager authenticationManager;
    private final LoginAttemptCommandHelper loginAttemptCommandHelper;
    private final DomainService domainService;

    @Transactional
    public UserAuthenticateEvent persisLoginAttempt(LoginCommand loginCommand) {
        log.info("Persisting login attempt to user's email= {}", loginCommand.getEmail());
        UserAuthenticateEvent userAuthenticateEvent = domainService.authenticateUser();
        LoginAttempt loginAttempt = userAuthenticateEvent.getLoginAttempt();
        authenticateUser(loginCommand, loginAttempt);
        loginAttemptCommandHelper.saveLoginAttempt(loginAttempt);
        log.info("Login attempt saved successfully with id= {}", loginAttempt.getId().getValue());
        return userAuthenticateEvent;
    }

    private void authenticateUser(LoginCommand loginCommand, LoginAttempt loginAttempt) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCommand.getEmail(), loginCommand.getRawPassword()));
        }
        catch (BadCredentialsException exc) {
            loginAttempt.setFailure(exc.getMessage());
            loginAttemptCommandHelper.saveLoginAttempt(loginAttempt);
            log.error(exc.getLocalizedMessage(), exc);
            throw new BadCredentialsException("Authentication for login fails cause of bad credentials \t error= " + exc.getMessage());
        }
    }

}
