package com.dev.batu.authentication_module.messaging.config.consumer;

import com.dev.batu.authentication_module.messaging.config.data.common.KafkaCommonConfigData;
import com.dev.batu.authentication_module.messaging.config.data.consumer.KafkaConsumerConfigData;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfig<K, V> {

    private final KafkaCommonConfigData commonData;
    private final KafkaConsumerConfigData consumerData;

    public Map<String, Object> props(){
        HashMap<java.lang.String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, commonData.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerData.getGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumerData.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, consumerData.getValueDeserializer());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, consumerData.getSessionTimeoutMs());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumerData.getMaxPollRecords());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerData.getAutoOffsetReset());
        return props;
    }

    public DefaultKafkaConsumerFactory<K, V> factory(){
        JsonDeserializer<V> jsonDeserializer = new JsonDeserializer<>(Object.class);
        jsonDeserializer.addTrustedPackages("*");
        // To do: Null ?
        return new DefaultKafkaConsumerFactory<>(props(), null, jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<K, V> containerFactory(){
        ConcurrentKafkaListenerContainerFactory<K, V> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(factory());
        containerFactory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return containerFactory;
    }
}
