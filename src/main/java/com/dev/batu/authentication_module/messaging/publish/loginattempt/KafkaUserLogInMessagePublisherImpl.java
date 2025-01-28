package com.dev.batu.authentication_module.messaging.publish.loginattempt;

import com.dev.batu.authentication_module.domain.event.loginAttempt.UserAuthenticateEvent;
import com.dev.batu.authentication_module.ports.input.message.KafkaUserLogInMessagePublisher;
import org.springframework.stereotype.Component;

@Component
public class KafkaUserLogInMessagePublisherImpl implements KafkaUserLogInMessagePublisher {
    @Override
    public void send(UserAuthenticateEvent userAuthenticateEvent) {

    }
}
