package com.assessment.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesEventValue {
    private String catalogNumber;
    private String orderNumber;
    private String quantity;
    private String salesDate;
    private String country;
}
