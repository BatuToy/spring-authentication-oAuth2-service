package com.dev.batu.authentication_module.domain;

import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.event.loginattempt.UserAuthenticateEvent;
import com.dev.batu.authentication_module.domain.event.user.UserAuthorizedEvent;
import com.dev.batu.authentication_module.domain.event.user.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.dev.batu.authentication_module.common.constant.AppConstants.*;

@Slf4j
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

    @Override
    public UserAuthorizedEvent authorizeUser(User user) {
        user.validateRoleAdmin();
        user.initializeAdminRole();
        log.info("Domain user initialized for role_admin!");
        return new UserAuthorizedEvent(user, ZonedDateTime.now(ZoneId.of(UTC)));
    }

}
