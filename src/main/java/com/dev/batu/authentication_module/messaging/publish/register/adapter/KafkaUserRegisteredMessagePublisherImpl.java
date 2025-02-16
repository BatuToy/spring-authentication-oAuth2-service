package com.dev.batu.authentication_module.messaging.publish.register.adapter;

import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.event.user.UserRegisteredEvent;
import com.dev.batu.authentication_module.messaging.producer.KafkaProducer;
import com.dev.batu.authentication_module.ports.output.message.KafkaUserRegisteredMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaUserRegisteredMessagePublisherImpl implements KafkaUserRegisteredMessagePublisher {

    private final KafkaProducer<String, User> kafkaProducer;

    @Override
    public void publish(UserRegisteredEvent userRegisteredEvent, String topic) {
        User user = userRegisteredEvent.getUser();
        final String key = UUID.randomUUID().toString();
        kafkaProducer.send(key, user, topic);
    }
}
