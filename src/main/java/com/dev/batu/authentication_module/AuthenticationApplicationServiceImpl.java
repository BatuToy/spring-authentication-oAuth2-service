package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.dto.login.LoginCommand;
import com.dev.batu.authentication_module.dto.login.LoginResponse;
import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.dto.register.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Service
@Validated
public class AuthenticationApplicationServiceImpl implements AuthenticationApplicationService {

    private final RegisterCommandHandler registerCommandHandler;
    private final LoginCommandHandler loginCommandHandler;

    @Override
    public RegisterResponse register(RegisterCommand registerCommand) {
        return registerCommandHandler.register(registerCommand);
    }

    @Override
    public LoginResponse login(LoginCommand loginCommand) {
        return loginCommandHandler.login(loginCommand);
    }
}