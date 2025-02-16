package com.dev.batu.authentication_module.messaging.publish.loginattempt.adapter;

import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.event.loginAttempt.UserAuthenticateEvent;
import com.dev.batu.authentication_module.messaging.producer.KafkaProducer;
import com.dev.batu.authentication_module.ports.output.message.KafkaUserLogInMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaUserLogInMessagePublisherImpl implements KafkaUserLogInMessagePublisher {

    private final KafkaProducer<String, LoginAttempt> kafkaProducer;

    @Override
    public void publish(UserAuthenticateEvent userAuthenticateEvent, String topic){
        LoginAttempt loginAttempt = userAuthenticateEvent.getLoginAttempt();
        final String key = UUID.randomUUID().toString();
        kafkaProducer.send(key, loginAttempt, topic);
    }
}
