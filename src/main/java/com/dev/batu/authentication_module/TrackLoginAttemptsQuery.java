package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.attempt.LoginAttempt;
import com.dev.batu.authentication_module.dto.login.TrackLoginAttemptsResponse;
import com.dev.batu.authentication_module.mapper.AuthDataMapper;
import com.dev.batu.authentication_module.ports.output.LoginAttemptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrackLoginAttemptsQuery {

    private final LoginAttemptRepository loginAttemptRepository;
    private final AuthDataMapper authDataMapper;

    @Transactional(readOnly = true)
    public TrackLoginAttemptsResponse trackLoginAttemptsQuery(){
        log.info("Listing all the login attempts");
        List<LoginAttempt> loginAttempts = loginAttemptRepository.listAllAttempts();
        return new TrackLoginAttemptsResponse(
                authDataMapper.loginAttemptsToLoginAttemptResults(loginAttempts),
                "Login attempts listed successfully!"
        );
    }
}
