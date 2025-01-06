package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.attempt.LoginAttempt;
import com.dev.batu.authentication_module.exception.DomainException;
import com.dev.batu.authentication_module.ports.output.LoginAttemptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginAttemptCommandHelper {

    private final LoginAttemptRepository loginAttemptRepository;
    // Todo: In this new proxy issues of transactions the saving logic can take in to another class annotate as an
    // Propagation.RequiresNew
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLoginAttempt(LoginAttempt loginAttempt){
        LoginAttempt result = loginAttemptRepository.save(loginAttempt);
        if(result == null){
            log.error("Login attempt can not save for attempt id= {}", loginAttempt.getId().getValue());
            throw new DomainException("Login attempt can not save successfully!");
        }
    }
}
