package com.dev.batu.authentication_module.ports.output.message;

import com.dev.batu.authentication_module.common.event.Message;
import com.dev.batu.authentication_module.domain.event.loginAttempt.UserAuthenticateEvent;

public interface KafkaUserLogInMessagePublisher extends Message<UserAuthenticateEvent> {
    void publish(UserAuthenticateEvent userAuthenticateEvent, String topic);
}
