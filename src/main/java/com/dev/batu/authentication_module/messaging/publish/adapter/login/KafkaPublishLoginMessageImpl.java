package com.dev.batu.authentication_module.messaging.publish.adapter.login;

import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.event.loginattempt.UserAuthenticateEvent;
import com.dev.batu.authentication_module.messaging.producer.KafkaProducer;
import com.dev.batu.authentication_module.ports.output.message.KafkaPublishLoginMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaPublishLoginMessageImpl implements KafkaPublishLoginMessage {

    private final KafkaProducer<String, LoginAttempt> kafkaProducer;

    @Override
    public void publish(UserAuthenticateEvent userAuthenticateEvent, String topic){
        LoginAttempt loginAttempt = userAuthenticateEvent.getLoginAttempt();
        final String key = UUID.randomUUID().toString();
        kafkaProducer.send(key, loginAttempt, topic);
    }
}
