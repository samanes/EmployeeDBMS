package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link EmailValidator} utility class.
 */
class EmailValidatorTest {

    @Test
    void testValidEmail() {
        EmailValidator.validate("test@example.com");
        EmailValidator.validate("user.name@domain.co.in");
        EmailValidator.validate("user_name123@domain.org");
        EmailValidator.validate("username+123@domain.com");
    }

    @Test
    void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> EmailValidator.validate("invalid-email"));
        assertThrows(IllegalArgumentException.class, () -> EmailValidator.validate("user@domain@domain.com"));
        assertThrows(IllegalArgumentException.class, () -> EmailValidator.validate("user@domain"));
        assertThrows(IllegalArgumentException.class, () -> EmailValidator.validate("user@.com"));
        assertThrows(IllegalArgumentException.class, () -> EmailValidator.validate("user@domain..com"));
    }

    @Test
    void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> EmailValidator.validate(null));
    }
}
