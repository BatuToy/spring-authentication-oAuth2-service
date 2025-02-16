package com.dev.batu.authentication_module.messaging.config.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.dev.batu.authentication_module.common.constant.AppConstants.*;

@Configuration
public class CreateTopicConfig {

    @Bean
    public NewTopic registerTopic(){
        return TopicBuilder
                .name(TOPIC_REGISTER)
                .replicas(ONE)
                .partitions(ONE)
                .build();
    }

    @Bean
    public NewTopic loginTopic(){
        return TopicBuilder
                .name(TOPIC_LOGIN)
                .replicas(ONE)
                .partitions(ONE)
                .build();
    }
}
