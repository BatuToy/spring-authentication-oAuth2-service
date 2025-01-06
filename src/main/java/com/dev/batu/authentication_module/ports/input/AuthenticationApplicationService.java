package com.dev.batu.authentication_module.ports.input;

import com.dev.batu.authentication_module.dto.login.TrackLoginAttemptsResponse;
import com.dev.batu.authentication_module.dto.login.LoginCommand;
import com.dev.batu.authentication_module.dto.login.LoginResponse;
import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.dto.register.RegisterResponse;
import jakarta.validation.Valid;

public interface AuthenticationApplicationService {
    LoginResponse login(@Valid  LoginCommand loginCommand);
    RegisterResponse register(@Valid RegisterCommand registerCommand);
    TrackLoginAttemptsResponse trackLoginAttempts();
}
