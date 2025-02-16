package com.dev.batu.authentication_module.messaging.config.producer;

import com.dev.batu.authentication_module.messaging.config.data.common.KafkaCommonConfigData;
import com.dev.batu.authentication_module.messaging.config.data.consumer.KafkaConsumerConfigData;
import com.dev.batu.authentication_module.messaging.config.data.producer.KafkaProducerConfigData;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaProducerConfig<K, V> {

    private final KafkaProducerConfigData producerData;
    private final KafkaCommonConfigData commonData;

    @Bean
    public KafkaTemplate<K, V> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    public Map<String, Object> props(){
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, commonData.getBootstrapServers());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producerData.getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producerData.getValueSerializer());
        props.put(ProducerConfig.ACKS_CONFIG, producerData.getAcks());
        return props;
    }

    @Bean
    public DefaultKafkaProducerFactory<K, V> producerFactory(){
        return new DefaultKafkaProducerFactory<>(props());
    }
}
