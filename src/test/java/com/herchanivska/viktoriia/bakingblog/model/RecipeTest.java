package com.herchanivska.viktoriia.bakingblog.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeTest {
    private static Recipe validRecipe;
    private static User validUser;
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validRecipe = new Recipe();
        validUser = new User();
        validUser.setId(1);
        validUser.setEmail("some@mail.com");
        validUser.setUsername("username");
        validUser.setPassword("MyP4ssw()rd");
        validUser.setBirthDate(LocalDate.of(1990, 12, 4));
    }

    @BeforeEach
    void setValidRecipe() {
        validRecipe.setName("Recipe name");
        validRecipe.setCookingTimeMinutes(5);
        validRecipe.setAuthor(validUser);
    }

    @Test
    void recipeValid() {
        Set<ConstraintViolation<Recipe>> violations = validator.validate(validRecipe);
        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideValidName")
    void recipeValidName(String input) {
        Recipe valid = validRecipe;
        valid.setName(input);
        Set<ConstraintViolation<Recipe>> violations = validator.validate(valid);
        assertEquals(0, violations.size());
    }

    private static Stream<Arguments> provideValidName() {
        return Stream.of(
                Arguments.of("Valid Name"),
                Arguments.of("Valid name"),
                Arguments.of("VALID NAME"),
                Arguments.of("valid name"),
                Arguments.of("VALID"),
                Arguments.of("valid"),
                Arguments.of("Valid"),
                Arguments.of("This is ALSO Valid")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidName")
    void recipeInvalidName(String input, String message) {
        Recipe invalid = validRecipe;
        invalid.setName(input);
        Set<ConstraintViolation<Recipe>> violations = validator.validate(invalid);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
        assertEquals(message, violations.iterator().next().getMessage());
    }

    private static Stream<Arguments> provideInvalidName() {
        String messageNull = "Recipe name can not be null";
        String messageInvalid = "Recipe name can contain only letters and spaces between words";
        return Stream.of(
                Arguments.of(null, messageNull),
                Arguments.of("Inv4lid", messageInvalid),
                Arguments.of("Invalid ", messageInvalid),
                Arguments.of(" invalid", messageInvalid),
                Arguments.of("Invalid*", messageInvalid)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidCookingTime")
    void recipeValidCookingTime(int input) {
        Recipe valid = validRecipe;
        valid.setCookingTimeMinutes(input);
        Set<ConstraintViolation<Recipe>> violations = validator.validate(valid);
        assertEquals(0, violations.size());
    }

    private static Stream<Arguments> provideValidCookingTime() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(20),
                Arguments.of(156)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCookingTime")
    void recipeInvalidCookingTime(int input) {
        Recipe invalid = validRecipe;
        invalid.setCookingTimeMinutes(input);
        Set<ConstraintViolation<Recipe>> violations = validator.validate(invalid);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
        assertEquals("Cooking time should be at least 1 minute", violations.iterator().next().getMessage());
    }

    private static Stream<Arguments> provideInvalidCookingTime() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(-1),
                Arguments.of(-24)
        );
    }

    @Test
    void recipeInvalidUser() {
        Recipe invalid = validRecipe;
        invalid.setAuthor(null);
        Set<ConstraintViolation<Recipe>> violations = validator.validate(invalid);
        assertEquals(1, violations.size());
        assertNull(violations.iterator().next().getInvalidValue());
        assertEquals("Recipe author can not be null", violations.iterator().next().getMessage());
    }
}