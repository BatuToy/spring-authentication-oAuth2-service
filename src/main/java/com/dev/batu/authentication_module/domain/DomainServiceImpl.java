package com.dev.batu.authentication_module.domain;

import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.event.user.UserCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DomainServiceImpl implements DomainService{

    @Override
    public UserCreatedEvent initializeUser(User user) {
        user.validateUser();
        user.initializeUser();
        return new UserCreatedEvent(user, ZonedDateTime.now(ZoneId.of("UTC")));
    }

}
