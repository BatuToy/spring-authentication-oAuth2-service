package com.dev.batu.authentication_module.domain.event.loginattempt;

import com.dev.batu.authentication_module.domain.entity.LoginAttempt;

import java.time.ZonedDateTime;

public class UserAuthenticateEvent extends LoginAttemptEvent {
    public UserAuthenticateEvent(LoginAttempt loginAttempt, ZonedDateTime createdAt) {
        super(loginAttempt, createdAt);
    }
}
