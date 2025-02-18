package com.dev.batu.authentication_module.domain;

import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.event.user.UserAuthorizedEvent;
import com.dev.batu.authentication_module.domain.event.loginattempt.UserAuthenticateEvent;
import com.dev.batu.authentication_module.domain.event.user.UserRegisteredEvent;

public interface DomainService {
    UserRegisteredEvent initializeUser(User user);
    UserAuthorizedEvent authorizeUser(User user);
    UserAuthenticateEvent authenticateUser();
}
