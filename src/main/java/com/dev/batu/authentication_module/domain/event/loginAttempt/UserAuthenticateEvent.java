package com.dev.batu.authentication_module.domain.event.loginAttempt;

import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.event.user.UserEvent;

import java.time.ZonedDateTime;

public class UserAuthenticateEvent extends LoginAttemptEvent {
    public UserAuthenticateEvent(LoginAttempt loginAttempt, ZonedDateTime createdAt) {
        super(loginAttempt, createdAt);
    }
}
