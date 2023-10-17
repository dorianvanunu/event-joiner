package com.assessment.consumer;

import com.assessment.model.event.AuditData;
import com.assessment.model.event.EventKey;
import com.assessment.model.event.RegistrationEvent;
import com.assessment.model.event.RegistrationEventValue;
import com.assessment.service.EventJoinService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Service
public class RegistrationEventConsumer {
    @Autowired
    private EventJoinService eventJoinService;

    @KafkaListener(topics = "topic.a", groupId = "event-processing-group")
    public void consumeMessage(RegistrationEvent registrationEvent) {
        EventKey key = registrationEvent.getKey();
        if (Objects.equals(key.getCountry(), "001")) {
            RegistrationEventValue registrationEventValue = registrationEvent.getValue();
            eventJoinService.addRegistrationEvent(registrationEvent);
        }
    }
}
