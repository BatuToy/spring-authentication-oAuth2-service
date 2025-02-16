package com.dev.batu.authentication_module.messaging.config.data.producer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-producer")
@Data
public class KafkaProducerConfigData {
    private String keySerializer;
    private String valueSerializer;
    private String acks;
}
