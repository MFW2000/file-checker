package com.mwf.filechecker.exception;

/**
 * Thrown to indicate that something went wrong with a certain {@code FileChecker} method.
 */
public class FileCheckerException extends Exception {
    /**
     * Constructs an {@code FileCheckerException} with the specified detail message.
     * @param message the detail message
     */
    public FileCheckerException(String message) {
        super(message);
    }
}
