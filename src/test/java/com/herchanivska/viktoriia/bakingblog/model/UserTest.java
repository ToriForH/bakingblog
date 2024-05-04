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
class UserTest {
    private static User validUser;
    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    @BeforeAll
    static void init() {
        validUser = new User();
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    @BeforeEach
    void setValidUser() {
        validUser.setEmail("my_valid.email1@mail.com");
        validUser.setUsername("User_name-val1d");
        validUser.setPassword("Qw1!5678");
        validUser.setBirthDate(LocalDate.now().minusYears(5));
    }

    @Test
    void userValid() {
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideValidEmail")
    void userValidEmail(String input) {
        User valid = validUser;
        valid.setEmail(input);
        Set<ConstraintViolation<User>> violations = validator.validate(valid);
        assertEquals(0, violations.size());
    }

    private static Stream<Arguments> provideValidEmail() {
        return Stream.of(
                Arguments.of("valid.email@mail.com"),
                Arguments.of("valid1@mail.ua"),
                Arguments.of("my_valid@mail.org"),
                Arguments.of("my@mail.valid.com"),
                Arguments.of("this@mail.val1d.com"),
                Arguments.of("this@is-valid.com"),
                Arguments.of("mail@is_valid.com")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidEmail")
    void userInvalidEmail(String input, String message) {
        User invalid = validUser;
        invalid.setEmail(input);
        Set<ConstraintViolation<User>> violations = validator.validate(invalid);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
        assertEquals(message, violations.iterator().next().getMessage());
    }

    private static Stream<Arguments> provideInvalidEmail() {
        String messageNull = "Email can not be null";
        String messageInvalid = "Please provide valid email";
        return Stream.of(
                Arguments.of(null, messageNull),
                Arguments.of("invalid@m.o", messageInvalid),
                Arguments.of("email@invalid.random", messageInvalid),
                Arguments.of("inval!d@mail.com", messageInvalid),
                Arguments.of("invalid@e-ma!l.org", messageInvalid),
                Arguments.of("invali@mail.o*g", messageInvalid),
                Arguments.of("        ", messageInvalid)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidUsername")
    void userValidUsername(String input) {
        User valid = validUser;
        valid.setUsername(input);
        Set<ConstraintViolation<User>> violations = validator.validate(valid);
        assertEquals(0, violations.size());
    }

    private static Stream<Arguments> provideValidUsername() {
        return Stream.of(
                Arguments.of("valid"),
                Arguments.of("VALID"),
                Arguments.of("Valid"),
                Arguments.of("Val1d"),
                Arguments.of("Username-Valid"),
                Arguments.of("valid_username"),
                Arguments.of("An0ther-valid"),
                Arguments.of("123"),
                Arguments.of("1234567890123456789012345")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidUsername")
    void userInvalidUsername(String input, String message) {
        User invalid = validUser;
        invalid.setUsername(input);
        Set<ConstraintViolation<User>> violations = validator.validate(invalid);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
        assertEquals(message, violations.iterator().next().getMessage());
    }

    private static Stream<Arguments> provideInvalidUsername() {
        String messageNull = "Username can not be null";
        String messageInvalid = "Username could contain only letters, numbers, dash and underscore";
        String messageInvalidSize = "Username should be more than or equal to 3 and less than or equal to 25";
        return Stream.of(
                Arguments.of(null, messageNull),
                Arguments.of("invalid@", messageInvalid),
                Arguments.of("###", messageInvalid),
                Arguments.of("in", messageInvalidSize),
                Arguments.of("        ", messageInvalid),
                Arguments.of("inval123456789012345678901", messageInvalidSize)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidPassword")
    void userValidPassword(String input) {
        User valid = validUser;
        valid.setPassword(input);
        Set<ConstraintViolation<User>> violations = validator.validate(valid);
        assertEquals(0, violations.size());
    }

    private static Stream<Arguments> provideValidPassword() {
        return Stream.of(
                Arguments.of("valid.Password1"),
                Arguments.of("VALID PASSWORD&1e"),
                Arguments.of("my_Valid@password2")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPassword")
    void userInvalidPassword(String input, String message) {
        User invalid = validUser;
        invalid.setPassword(input);
        Set<ConstraintViolation<User>> violations = validator.validate(invalid);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
        assertEquals(message, violations.iterator().next().getMessage());
    }

    private static Stream<Arguments> provideInvalidPassword() {
        String messageNull = "Password can not be null";
        String messageInvalid = "Password should contain at least one lower case letter, " +
                "one upper case letter, one digit, one special character";
        String messageInvalidSize = "Password should be at least 8 characters in length";
        return Stream.of(
                Arguments.of(null, messageNull),
                Arguments.of("inval1d&", messageInvalid),
                Arguments.of("INVALIDPASSWORD", messageInvalid),
                Arguments.of("invalidpassword", messageInvalid),
                Arguments.of("ANOTHER_INV4LID", messageInvalid),
                Arguments.of("!1&^123456", messageInvalid),
                Arguments.of("12345678", messageInvalid),
                Arguments.of("!@#$%^&*()", messageInvalid),
                Arguments.of("Inv4vid123", messageInvalid),
                Arguments.of("Inval*dpass", messageInvalid),
                Arguments.of("        ", messageInvalid),
                Arguments.of("Inv4l!d", messageInvalidSize)

        );
    }

    @ParameterizedTest
    @MethodSource("provideValidBirthDate")
    void userValidBirthDate(LocalDate input) {
        User valid = validUser;
        valid.setBirthDate(input);
        Set<ConstraintViolation<User>> violations = validator.validate(valid);
        assertEquals(0, violations.size());
    }

    private static Stream<Arguments> provideValidBirthDate() {
        LocalDate today = LocalDate.now();
        return Stream.of(
                Arguments.of(today.minusDays(1)),
                Arguments.of(today.minusWeeks(1)),
                Arguments.of(today.minusMonths(1)),
                Arguments.of(today.minusYears(1))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidBirthDate")
    void userInvalidEmail(LocalDate input, String message) {
        User invalid = validUser;
        invalid.setBirthDate(input);
        Set<ConstraintViolation<User>> violations = validator.validate(invalid);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
        assertEquals(message, violations.iterator().next().getMessage());
    }

    private static Stream<Arguments> provideInvalidBirthDate() {
        LocalDate today = LocalDate.now();
        String message = "Birth date should be before today";
        return Stream.of(
                Arguments.of(today, message),
                Arguments.of(today.plusDays(1), message),
                Arguments.of(today.plusWeeks(1), message),
                Arguments.of(today.plusMonths(1), message),
                Arguments.of(today.plusYears(1), message)
        );
    }
}