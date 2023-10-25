package com.mwf.filechecker.exception;

/**
 * Thrown to indicate that invalid input was provided.
 */
public class InvalidInputException extends Exception {
    /**
     * Constructs an {@code InvalidInputException} with the specified detail message.
     * @param message the detail message
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
