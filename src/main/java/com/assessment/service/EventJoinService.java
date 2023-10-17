package com.assessment.service;

import java.util.*;

import com.assessment.model.event.*;
import org.springframework.stereotype.Service;

@Service
public class EventJoinService {
    Map<EventKey, RegistrationEvent> registrationEvents = new HashMap<EventKey, RegistrationEvent>();

    public void addRegistrationEvent(RegistrationEvent registrationEvent) {
        registrationEvents.put(registrationEvent.getKey(), registrationEvent);
    }

    public Optional<CombinedEvent> joinSalesEvent(SalesEvent salesEvent) {
        RegistrationEvent registrationEvent = registrationEvents.get(salesEvent.getKey());

        if (registrationEvent != null) {
            EventKey key = salesEvent.getKey();

            AuditData auditData = new AuditData("DetailedSalesEvent", "SLS");

            CombinedEventValue value = new CombinedEventValue();

            value.setModel(registrationEvent.getValue().getModel());
            value.setCountry(registrationEvent.getValue().getCountry());
            value.setQuantity(salesEvent.getValue().getQuantity());
            value.setSelling(registrationEvent.getValue().isSelling());
            value.setSellingStatusDate(registrationEvent.getValue().getSellingStatusDate());

            return Optional.of(new CombinedEvent(key, value, auditData));
        }

        return Optional.empty();
    }
}
