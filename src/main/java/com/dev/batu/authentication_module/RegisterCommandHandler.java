package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.event.user.UserCreatedEvent;
import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.dto.register.RegisterResponse;
import com.dev.batu.authentication_module.mapper.UserDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RegisterCommandHandler {

    private final RegisterCommandHelper registerCommandHelper;
    private final UserDataMapper userDataMapper;

    public RegisterResponse register(RegisterCommand registerCommand) {
        log.info("User starting to creating for username= {}", registerCommand.getUserName());
        UserCreatedEvent userCreatedEvent = registerCommandHelper.persistUser(registerCommand);
        return userDataMapper.domainUserToRegisterResponse(userCreatedEvent.getUser(),
                "User registered with user id= " + userCreatedEvent.getUser().getId().getValue());
    }



}
