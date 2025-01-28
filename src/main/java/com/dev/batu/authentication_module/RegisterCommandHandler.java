package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.event.user.UserRegisteredEvent;
import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.dto.register.RegisterResponse;
import com.dev.batu.authentication_module.mapper.UserDataMapper;
import com.dev.batu.authentication_module.ports.input.message.KafkaUserRegisteredMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RegisterCommandHandler {

    private final RegisterCommandHelper registerCommandHelper;
    private final KafkaUserRegisteredMessagePublisher kafkaUserRegisteredMessagePublisher;
    private final UserDataMapper userDataMapper;

    public RegisterResponse register(RegisterCommand registerCommand) {
        log.info("User starting to creating for username= {}", registerCommand.getUserName());
        UserRegisteredEvent userRegisteredEvent = registerCommandHelper.persistUser(registerCommand);
        kafkaUserRegisteredMessagePublisher.send(userRegisteredEvent);
        return userDataMapper.domainUserToRegisterResponse(userRegisteredEvent.getUser(),
                "User registered with user id= " + userRegisteredEvent.getUser().getId().getValue());
    }
}
