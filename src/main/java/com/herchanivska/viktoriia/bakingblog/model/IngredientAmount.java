package com.herchanivska.viktoriia.bakingblog.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class IngredientAmount {
    @Min(value = 1, message = "Amount of ingredient must be at least 1")
    private int amount;

    @Enumerated(EnumType.STRING)
    private MeasureUnit measureUnit;
}
