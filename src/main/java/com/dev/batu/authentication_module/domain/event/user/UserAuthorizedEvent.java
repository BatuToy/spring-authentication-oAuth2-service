package com.dev.batu.authentication_module.domain.event.user;

import com.dev.batu.authentication_module.domain.aggregateroot.User;

import java.time.ZonedDateTime;

public class UserAuthorizedEvent extends UserEvent {

    public UserAuthorizedEvent(User user, ZonedDateTime createdAt) {
        super(user, createdAt);
    }
}
