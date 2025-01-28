package com.dev.batu.authentication_module.domain;

import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.event.loginAttempt.UserAuthenticateEvent;
import com.dev.batu.authentication_module.domain.event.user.UserRegisteredEvent;

public interface DomainService {
    UserRegisteredEvent initializeUser(User user);
    UserAuthenticateEvent authenticateUser();
}
