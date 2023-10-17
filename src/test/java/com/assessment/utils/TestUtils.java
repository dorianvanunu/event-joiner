package com.assessment.utils;

import com.assessment.model.event.*;

public class TestUtils {
    public static RegistrationEvent createRegistrationEvent(EventKey key) {
        RegistrationEventValue registrationEventValue = new RegistrationEventValue(
                "001", "int4123", true, "29525", "11", "int7218", "2023-06-30T18:21:31.000000Z", "REG03814"
        );
        AuditData auditDataRegistration = new AuditData("RGR", "Registration");
        return new RegistrationEvent(key, registrationEventValue, auditDataRegistration);
    }

    public static SalesEvent createSalesEvent(EventKey key) {
        SalesEventValue salesEventValue = new SalesEventValue("29525", "03814", "2", "2023-07-30T18:21:31.000000Z", "001");
        AuditData auditDataSales = new AuditData("SLS", "SalesEvent");
        return new SalesEvent(key, salesEventValue, auditDataSales);
    }
}
