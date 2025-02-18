package com.dev.batu.authentication_module.messaging.publish.adapter.authorize;

import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.event.user.UserAuthorizedEvent;
import com.dev.batu.authentication_module.messaging.producer.KafkaProducer;
import com.dev.batu.authentication_module.ports.output.message.KafkaPublishAuthorizeUserMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaPublishAuthorizeUserMessageImpl implements KafkaPublishAuthorizeUserMessage {

    private final KafkaProducer<User, String> kafkaProducer;

    @Override
    public void send(UserAuthorizedEvent event, String topic) {
        User user = event.getUser();
        String key = UUID.randomUUID().toString();
        kafkaProducer.send(user, key, topic);
    }
}
