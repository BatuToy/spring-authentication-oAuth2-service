package com.dev.batu.authentication_module.messaging.config.data.consumer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-consumer")
@Data
public class KafkaConsumerConfigData {
    private String groupId;
    private String keyDeserializer;
    private String valueDeserializer;
    private String sessionTimeoutMs;
    private String maxPollRecords;
    private String autoOffsetReset;
}
