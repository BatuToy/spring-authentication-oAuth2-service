package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.dto.login.LoginCommand;
import com.dev.batu.authentication_module.dto.login.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class LoginCommandHandler {

    @Transactional
    public LoginResponse login(LoginCommand loginCommand) {
        return null;
    }
}