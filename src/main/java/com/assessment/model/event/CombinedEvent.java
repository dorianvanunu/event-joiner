package com.assessment.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CombinedEvent {
    EventKey key;
    CombinedEventValue value;
    AuditData auditData;
}
