package com.dev.batu.authentication_module.domain;

import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.event.user.UserCreatedEvent;

public interface DomainService {
    UserCreatedEvent initializeUser(User user);
}
