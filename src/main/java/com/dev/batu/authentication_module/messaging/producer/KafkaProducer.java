package com.dev.batu.authentication_module.messaging.producer;

public interface KafkaProducer<K, V> {
    void send(K key, V message, String topic);
}
