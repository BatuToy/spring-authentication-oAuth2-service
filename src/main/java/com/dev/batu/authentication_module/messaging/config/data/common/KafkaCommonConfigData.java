package com.dev.batu.authentication_module.messaging.config.data.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
public class KafkaCommonConfigData {
    private String bootstrapServers;
}
