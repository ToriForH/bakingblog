package com.herchanivska.viktoriia.bakingblog.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IngredientAmountTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @ParameterizedTest
    @MethodSource("provideValidAmount")
    void ingredientAmountValidAmount(int input) {
        IngredientAmount valid = new IngredientAmount();
        valid.setMeasureUnit(MeasureUnit.GRAMS);
        valid.setAmount(input);
        Set<ConstraintViolation<IngredientAmount>> violations = validator.validate(valid);
        assertEquals(0, violations.size());
    }

    private static Stream<Arguments> provideValidAmount() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(37),
                Arguments.of(138),
                Arguments.of(1759)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAmount")
    void ingredientsAmount(int input) {
        IngredientAmount invalid = new IngredientAmount();
        invalid.setMeasureUnit(MeasureUnit.GRAMS);
        invalid.setAmount(input);
        Set<ConstraintViolation<IngredientAmount>> violations = validator.validate(invalid);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
        assertEquals("Amount of ingredient must be at least 1", violations.iterator().next().getMessage());
    }

    private static Stream<Arguments> provideInvalidAmount() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(-1),
                Arguments.of(-240)
        );
    }
}