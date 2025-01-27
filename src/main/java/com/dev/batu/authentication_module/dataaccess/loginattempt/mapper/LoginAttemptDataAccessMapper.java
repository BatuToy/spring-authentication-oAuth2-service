package com.dev.batu.authentication_module.dataaccess.loginattempt.mapper;

import com.dev.batu.authentication_module.dataaccess.loginattempt.entity.LoginAttemptEntity;
import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.valueobject.LoginAttemptId;
import org.springframework.stereotype.Component;

@Component
public class LoginAttemptDataAccessMapper {

    public LoginAttemptEntity loginAttemptToLoginAttemptEntity(LoginAttempt loginAttempt) {
        return LoginAttemptEntity.builder()
                .id(loginAttempt.getId().getValue())
                .attemptedAt(loginAttempt.getAttemptedAt())
                .excMessage(loginAttempt.getExcMessage())
                .isSuccess(loginAttempt.isSuccess())
                .build();
    }

    public LoginAttempt loginAttemptEntityToLoginAttempt(LoginAttemptEntity loginAttemptEntity) {
        return new LoginAttempt(
                new LoginAttemptId(loginAttemptEntity.getId()),
                loginAttemptEntity.getAttemptedAt(),
                loginAttemptEntity.getExcMessage(),
                loginAttemptEntity.isSuccess()
        );
    }
}
