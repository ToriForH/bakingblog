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

class IngredientTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @ParameterizedTest
    @MethodSource("provideValidName")
    void ingredientValidName(String input) {
        Ingredient valid = new Ingredient();
        valid.setName(input);
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(valid);
        assertEquals(0, violations.size());
    }

    private static Stream<Arguments> provideValidName() {
        return Stream.of(
                Arguments.of("Valid"),
                Arguments.of("VALID"),
                Arguments.of("valid"),
                Arguments.of("1"),
                Arguments.of("V4lid"),
                Arguments.of("Valid-ingredient"),
                Arguments.of("ingredient-valid"),
                Arguments.of("Valid name"),
                Arguments.of("this-name valid"),
                Arguments.of("THIS valid-N4me"),
                Arguments.of("valid12"),
                Arguments.of("12 4-5"),
                Arguments.of("11 4"),
                Arguments.of("This also valid name")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidName")
    void ingredientInvalidName(String input, String message) {
        Ingredient invalid = new Ingredient();
        invalid.setName(input);
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(invalid);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
        assertEquals(message, violations.iterator().next().getMessage());
    }

    private static Stream<Arguments> provideInvalidName() {
        String messageNull = "Ingredient name can not be null";
        String messageInvalid = "Ingredient name should contain only letters, numbers, dash or spaces between words";
        return Stream.of(
                Arguments.of(null, messageNull),
                Arguments.of("  ", messageInvalid),
                Arguments.of("Inv*lid", messageInvalid),
                Arguments.of(" invalid", messageInvalid),
                Arguments.of("Invalid ", messageInvalid),
                Arguments.of("Invalid name *", messageInvalid)
        );
    }
}