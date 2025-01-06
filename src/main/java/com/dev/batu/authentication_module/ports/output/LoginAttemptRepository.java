package com.dev.batu.authentication_module.ports.output;

import com.dev.batu.authentication_module.domain.attempt.LoginAttempt;

import java.util.List;

public interface LoginAttemptRepository {
    LoginAttempt save(LoginAttempt loginAttempt);
    List<LoginAttempt> listAllAttempts();
}
