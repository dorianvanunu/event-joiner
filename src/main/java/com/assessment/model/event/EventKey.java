package com.assessment.model.event;

import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventKey {
    String country;
    String catalog_number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventKey otherKey = (EventKey) o;
        return Objects.equals(country, otherKey.country) &&
                Objects.equals(catalog_number, otherKey.catalog_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, catalog_number);
    }
}
