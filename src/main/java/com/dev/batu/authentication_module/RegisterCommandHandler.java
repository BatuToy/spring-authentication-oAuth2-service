package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.event.user.UserRegisteredEvent;
import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.dto.register.RegisterResponse;
import com.dev.batu.authentication_module.mapper.UserDataMapper;
import com.dev.batu.authentication_module.ports.output.message.KafkaPublishRegisterMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.dev.batu.authentication_module.common.constant.AppConstants.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class RegisterCommandHandler {

    private final RegisterCommandHelper registerCommandHelper;
    private final KafkaPublishRegisterMessage kafkaUserRegisteredMessagePublisher;
    private final UserDataMapper userDataMapper;

    public RegisterResponse register(RegisterCommand registerCommand) {
        log.info("User starting to creating for username= {}", registerCommand.getUserName());
        UserRegisteredEvent userRegisteredEvent = registerCommandHelper.persistUser(registerCommand);
        kafkaUserRegisteredMessagePublisher.publish(userRegisteredEvent, TOPIC_REGISTER);
        return userDataMapper.domainUserToRegisterResponse(userRegisteredEvent.getUser(),
                "User registered with user id= " + userRegisteredEvent.getUser().getId().getValue());
    }
}
