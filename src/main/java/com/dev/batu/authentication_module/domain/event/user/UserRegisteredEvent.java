package com.dev.batu.authentication_module.domain.event.user;

import com.dev.batu.authentication_module.domain.aggregateroot.User;

import java.time.ZonedDateTime;

public class UserRegisteredEvent extends UserEvent {
    public UserRegisteredEvent(User user, ZonedDateTime createdAt) {
        super(user, createdAt);
    }
}
