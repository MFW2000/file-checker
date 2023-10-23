package com.mwf.filechecker;

import com.mwf.filechecker.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class AppTest {
    @Test
    public void testValidPathInput() throws IOException, InvalidInputException {
        // Create temporary directory and get its path
        String input = String.valueOf(Files.createTempDirectory("directory"));

        provideInput(input);
        String returnValue = App.readPathInput();

        assertEquals(input, returnValue);
    }

    @Test
    public void testInvalidPathInput() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            provideInput("not a path");
            App.readPathInput();
        });

        String expectedMessage = "Invalid input, the provided path is not valid.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNonExistingPathInput() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            provideInput("/not/a/path");
            App.readPathInput();
        });

        String expectedMessage = "Invalid input, the provided path is not valid.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFileAsInvalidPathInput() throws IOException {
        // Create temporary directory, file, and get its path
        Path tempDirectory = Files.createTempDirectory("directory");
        String input = String.valueOf(Files.createTempFile(tempDirectory, "test", ".txt"));

        Exception exception = assertThrows(InvalidInputException.class, () -> {
            provideInput(input);
            App.readPathInput();
        });

        String expectedMessage = "Invalid input, the provided path is not valid.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testValidCharacterInput() throws InvalidInputException {
        provideInput("_");
        char returnValue = App.readCharacterInput();

        assertEquals('_', returnValue);
    }

    @Test
    public void testInvalidCharacterInput() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            provideInput("string");
            App.readCharacterInput();
        });

        String expectedMessage = "Invalid input, the provided input is not a single character.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testWhitespaceCharacterInput() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            provideInput(" ");
            App.readCharacterInput();
        });

        String expectedMessage = "Invalid input, the provided input is not a single character.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    private void provideInput(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }
}
