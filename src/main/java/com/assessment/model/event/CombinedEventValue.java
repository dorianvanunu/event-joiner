package com.assessment.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CombinedEventValue {
    private String catalogNumber;
    private String country;
    private String orderNumber;
    private String quantity;
    private boolean isSelling;
    private String model;
    private String productId;
    private String registrationId;
    private String registrationNumber;
    private String salesDate;
    private String sellingStatusDate;
}
