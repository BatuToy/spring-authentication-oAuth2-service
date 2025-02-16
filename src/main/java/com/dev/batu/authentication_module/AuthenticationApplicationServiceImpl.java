package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.dto.login.TrackLoginAttemptsResponse;
import com.dev.batu.authentication_module.dto.login.LoginCommand;
import com.dev.batu.authentication_module.dto.login.LoginResponse;
import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.dto.register.RegisterResponse;
import com.dev.batu.authentication_module.ports.input.AuthenticationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RequiredArgsConstructor
@Service
@Validated
public class AuthenticationApplicationServiceImpl implements AuthenticationApplicationService {

    private final RegisterCommandHandler registerCommandHandler;
    private final LoginCommandHandler loginCommandHandler;
    private final TrackLoginAttemptsQuery trackLoginAttemptsQuery;

    @Override
    public RegisterResponse register(RegisterCommand registerCommand) {
        return registerCommandHandler.register(registerCommand);
    }

    @Override
    public TrackLoginAttemptsResponse trackLoginAttempts() {
        return trackLoginAttemptsQuery.trackLoginAttemptsQuery();
    }

    @Override
    public LoginResponse login(@Valid LoginCommand loginCommand) {
        return loginCommandHandler.login(loginCommand);
    }
}
