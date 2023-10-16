package com.assessment.producer;

import com.assessment.model.event.CombinedEvent;
import com.assessment.model.event.EventKey;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CombinedEventProducer {
    private final KafkaTemplate<EventKey, CombinedEvent> kafkaTemplate;

    public CombinedEventProducer(KafkaTemplate<EventKey, CombinedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceCombinedEvent(CombinedEvent combinedEvent) {
        kafkaTemplate.send("topic.c", combinedEvent.getKey(), combinedEvent);
    }
}
