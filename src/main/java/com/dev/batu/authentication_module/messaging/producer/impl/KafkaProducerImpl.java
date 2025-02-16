package com.dev.batu.authentication_module.messaging.producer.impl;

import com.dev.batu.authentication_module.messaging.producer.KafkaProducer;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducerImpl<K, V> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void send(K key, V message, String topic) {
        // Todo: Error pop up while sending the message!
        CompletableFuture<SendResult<K, V>> future = kafkaTemplate.send(new ProducerRecord<>(topic, key, message));
        future.whenCompleteAsync(
                (ftr, exc) -> {
                    ProducerRecord<K, V> producerRecord = ftr.getProducerRecord();
                    RecordMetadata data = ftr.getRecordMetadata();
                    if (exc == null) {
                        log.info("Message with topic= {}, key= {}, value= {} sent successfully!",
                                producerRecord.topic(),
                                producerRecord.key(),
                                producerRecord.value());
                    } else {
                        log.error("Message with topic= {}, key= {}, offset= {}, value= {} can not be send!",
                                data.offset(),
                                data.topic(),
                                producerRecord.key(),
                                producerRecord.value().toString());
                        throw new KafkaProducerException(producerRecord,
                                "Message with topic= "
                                        + producerRecord.topic() + " key= "
                                        + producerRecord.key() + "offset= "
                                        + data.offset() + " value= "
                                        + producerRecord.value(),
                                exc.getCause());
                    }
                }
        );
    }
    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            kafkaTemplate.destroy();
        }
    }

}
