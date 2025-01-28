package com.dev.batu.authentication_module.domain.event.loginAttempt;

import com.dev.batu.authentication_module.common.event.DomainEvent;
import com.dev.batu.authentication_module.domain.entity.LoginAttempt;

import java.time.ZonedDateTime;

public abstract class LoginAttemptEvent implements DomainEvent<LoginAttempt> {
    private final LoginAttempt loginAttempt;
    private final ZonedDateTime zonedDateTime;

    public LoginAttemptEvent(LoginAttempt loginAttempt, ZonedDateTime zonedDateTime) {
        this.loginAttempt = loginAttempt;
        this.zonedDateTime = zonedDateTime;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public LoginAttempt getLoginAttempt() {
        return loginAttempt;
    }

}
