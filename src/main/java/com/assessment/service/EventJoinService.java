package com.assessment.service;

import java.util.*;

import com.assessment.model.event.*;
import org.springframework.stereotype.Service;

@Service
public class EventJoinService {
    Map<EventKey, RegistrationEvent> registrationEvents = new HashMap<EventKey, RegistrationEvent>();

    public void addRegistrationEvent(EventKey eventKey, RegistrationEvent registrationEvent) {
        registrationEvents.put(eventKey, registrationEvent);
    }

    public CombinedEvent joinSalesEvent(SalesEvent salesEvent) {
        RegistrationEvent registrationEvent = registrationEvents.get(salesEvent.getKey());

        EventKey key = salesEvent.getKey();

        AuditData auditData = new AuditData("DetailedSalesEvent", "SLS");

        CombinedEventValue value = new CombinedEventValue();

        value.setModel(registrationEvent.getValue().getModel());
        value.setCountry(registrationEvent.getValue().getCountry());
        value.setQuantity(salesEvent.getValue().getQuantity());

        return new CombinedEvent(key, value, auditData);

    }
}
