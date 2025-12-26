package com.airtribe.learntrack.Util;

import com.airtribe.learntrack.Exception.InvalidInputException;

public class InputValidator {

    public static String validateRequiredString(String input, String fieldName) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required and cannot be empty.");
        }

        if (!input.matches("[a-zA-Z ]+")) {
            throw new InvalidInputException(
                    fieldName + " must contain only alphabets."
            );
        }

        return input.trim();
    }

    public static String validateOptionalEmail(String email) throws InvalidInputException {

        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidInputException("Invalid email format.");
        }

        return email.trim();
    }
}
