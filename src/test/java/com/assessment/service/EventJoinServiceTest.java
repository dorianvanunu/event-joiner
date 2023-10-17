package com.assessment.service;

import com.assessment.model.event.*;
import com.assessment.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventJoinServiceTest {
    private final EventJoinService eventJoinService = new EventJoinService();
    @Test
    public void testJoinSalesEvent() {
        EventKey key = new EventKey("001", "1234");
        RegistrationEvent registrationEvent = TestUtils.createRegistrationEvent(key);
        eventJoinService.addRegistrationEvent(registrationEvent);
        SalesEvent salesEvent = TestUtils.createSalesEvent(key);
        Optional<CombinedEvent> combinedEvent = eventJoinService.joinSalesEvent(salesEvent);

        assertTrue(combinedEvent.isPresent());
        assertEquals("DetailedSalesEvent", combinedEvent.get().getAuditData().getEventName());
        assertEquals("SLS", combinedEvent.get().getAuditData().getSourceSystem());
        assertEquals(registrationEvent.getValue().getModel(), combinedEvent.get().getValue().getModel());
        assertEquals(registrationEvent.getValue().getCountry(), combinedEvent.get().getValue().getCountry());
        assertEquals(registrationEvent.getValue().isSelling(), combinedEvent.get().getValue().isSelling());
        assertEquals(registrationEvent.getValue().getSellingStatusDate(), combinedEvent.get().getValue().getSellingStatusDate());
        assertEquals(salesEvent.getValue().getQuantity(), combinedEvent.get().getValue().getQuantity());
    }
}
