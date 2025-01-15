package com.dev.batu.authentication_module.dataaccess.loginattempt.mapper;

import com.dev.batu.authentication_module.dataaccess.loginattempt.entity.LoginAttemptEntity;
import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.valueobject.LoginAttemptId;
import org.springframework.stereotype.Component;

@Component
public class LoginAttemptDataAccessMapper {

    public LoginAttemptEntity loginAttemptToLoginAttemptEntity(LoginAttempt loginAttempt){
        return LoginAttemptEntity.builder()
                .id(loginAttempt.getId().getValue())
                .attemptedAt(loginAttempt.getAttemptedAt())
                .isSuccess(loginAttempt.isSuccess())
                .build();
    }

    public LoginAttempt loginAttemptEntityToLoginAttempt(LoginAttemptEntity loginAttemptEntity){
        return LoginAttempt.builder()
                .loginAttemptId( new LoginAttemptId(loginAttemptEntity.getId()))
                .isSuccess(loginAttemptEntity.isSuccess())
                .attemptedAt(loginAttemptEntity.getAttemptedAt())
                .build();
    }
}
