package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.dto.login.TrackLoginAttemptsResponse;
import com.dev.batu.authentication_module.exception.NotFountException;
import com.dev.batu.authentication_module.mapper.UserDataMapper;
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
    private final UserDataMapper userDataMapper;

    @Transactional(readOnly = true)
    public TrackLoginAttemptsResponse trackLoginAttemptsQuery(){
        List<LoginAttempt> loginAttempts = loginAttemptRepository.listAllAttempts();
        if(loginAttempts == null){
            throw new NotFountException("Login attempts not found!");
        }
        return new TrackLoginAttemptsResponse(
                userDataMapper.loginAttemptsToLoginAttemptResults(loginAttempts),
                "Login attempts tracked successfully!"
        );
    }
}
