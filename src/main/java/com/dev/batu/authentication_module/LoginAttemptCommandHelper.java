package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.exception.DomainException;
import com.dev.batu.authentication_module.domain.exception.UserDomainException;
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLoginAttempt(LoginAttempt loginAttempt){
        LoginAttempt result = loginAttemptRepository.save(loginAttempt);
        if(result == null){
            log.error("Login attempt can not save for attempt id= {}", loginAttempt.getId().getValue());
            throw new UserDomainException("Login attempt can not save successfully!");
        }
    }
}
