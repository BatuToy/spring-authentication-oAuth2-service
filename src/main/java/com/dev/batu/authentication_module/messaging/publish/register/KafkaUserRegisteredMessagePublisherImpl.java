package com.dev.batu.authentication_module.messaging.publish.register;

import com.dev.batu.authentication_module.domain.event.user.UserRegisteredEvent;
import com.dev.batu.authentication_module.ports.input.message.KafkaUserRegisteredMessagePublisher;
import org.springframework.stereotype.Component;

@Component
public class KafkaUserRegisteredMessagePublisherImpl implements KafkaUserRegisteredMessagePublisher{

    @Override
    public void send(UserRegisteredEvent userRegisteredEvent) {

    }
}
