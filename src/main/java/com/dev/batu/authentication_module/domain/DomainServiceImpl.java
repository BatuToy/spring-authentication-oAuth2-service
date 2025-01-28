package com.dev.batu.authentication_module.domain;

import com.dev.batu.authentication_module.common.constant.Constants;
import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.event.loginAttempt.UserAuthenticateEvent;
import com.dev.batu.authentication_module.domain.event.user.UserRegisteredEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.dev.batu.authentication_module.common.constant.Constants.*;

public class DomainServiceImpl implements DomainService{

    @Override
    public UserRegisteredEvent initializeUser(User user) {
        user.validateUser();
        user.initializeUser();
        return new UserRegisteredEvent(user, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public UserAuthenticateEvent authenticateUser() {
        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.defaultAttempt();
        return new UserAuthenticateEvent(loginAttempt, ZonedDateTime.now(ZoneId.of(UTC)));
    }

}
