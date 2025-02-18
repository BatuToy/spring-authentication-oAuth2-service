package com.dev.batu.authentication_module.ports.output.message;

import com.dev.batu.authentication_module.common.event.Message;
import com.dev.batu.authentication_module.domain.event.user.UserRegisteredEvent;

public interface KafkaPublishRegisterMessage extends Message<UserRegisteredEvent> {
    void publish(UserRegisteredEvent userRegisteredEvent, String topic);
}
