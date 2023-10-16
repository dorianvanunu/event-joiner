package com.assessment.consumer;

import com.assessment.model.event.CombinedEvent;
import com.assessment.model.event.SalesEvent;
import com.assessment.producer.CombinedEventProducer;
import com.assessment.service.EventJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SalesEventConsumer {
    @Autowired
    CombinedEventProducer producer;

    @Autowired
    EventJoinService eventJoinService;

    @KafkaListener(topics = "topic.b", groupId = "event-processing-group")
    public void consumeMessage(SalesEvent salesEvent) {
        CombinedEvent combinedEvent = eventJoinService.joinSalesEvent(salesEvent);
        producer.produceCombinedEvent(combinedEvent);
    }
}
