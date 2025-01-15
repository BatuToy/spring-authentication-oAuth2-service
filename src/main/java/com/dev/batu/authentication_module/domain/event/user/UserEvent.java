package com.dev.batu.authentication_module.domain.event.user;

import com.dev.batu.authentication_module.common.DomainEvent;
import com.dev.batu.authentication_module.domain.aggregateroot.User;

import java.time.ZonedDateTime;

public abstract class UserEvent implements DomainEvent<User> {
    private final User user;
    private final ZonedDateTime createdAt;

    public UserEvent(User user, ZonedDateTime createdAt) {
        this.user = user;
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }
}
