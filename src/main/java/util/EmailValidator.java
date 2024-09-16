package util;

import java.util.regex.Pattern;

/**
 * Utility class for validating email addresses.
 */
public class EmailValidator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Validates the given email address.
     *
     * @param email the email to validate
     * @throws IllegalArgumentException if the email format is invalid
     */
    public static void validate(String email) {
        if (email == null || !PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}
