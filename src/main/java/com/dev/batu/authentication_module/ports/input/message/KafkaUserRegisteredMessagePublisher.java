package com.dev.batu.authentication_module.ports.input.message;

import com.dev.batu.authentication_module.common.event.Message;
import com.dev.batu.authentication_module.domain.event.user.UserRegisteredEvent;

public interface KafkaUserRegisteredMessagePublisher extends Message<UserRegisteredEvent> {
    void send(UserRegisteredEvent userRegisteredEvent);
}
