package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.dto.register.RegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class RegisterCommandHandler {

    @Transactional
    public RegisterResponse register(RegisterCommand registerCommand) {
        // Todo: check if there is any user that uses the email coming from the request!
        // Todo: If there is any user that uses the same email throw an EmailDuplicationException extends from AuthenticationException
        // Todo: Maybe write a domain service to handle the user logic in there before go to persist store!
        // Todo: Create a new user add in to the persist store! maybe flush imeaditly!
        return null;
    }
}
