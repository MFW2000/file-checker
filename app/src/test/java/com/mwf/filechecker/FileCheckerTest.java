package com.mwf.filechecker;

import com.mwf.filechecker.exception.FileCheckerException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileCheckerTest {
    @Test
    public void testGetFilesContainingCharacter() throws IOException, FileCheckerException {
        // Create temporary inspection directory
        Path tempDirectory = Files.createTempDirectory("test");

        // Create directory and file that should not be in the expected list
        Files.createTempDirectory(tempDirectory, "test_directory");
        Files.createTempFile(tempDirectory, "test-1", ".txt");

        // Create and assign files that should be in the expected list
        File file1 = Files.createTempFile(tempDirectory, "test_2", ".txt").toFile();
        File file2 = Files.createTempFile(tempDirectory, "test_3", ".txt").toFile();

        String directoryPath = tempDirectory.toString();
        char character = '_';

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(file1.getName());
        expectedResult.add(file2.getName());

        // TODO: Remove temporary test print
        System.out.println("Expected result:");
        expectedResult.forEach(System.out::println);

        List<String> actualResult = FileChecker.getFilesContainingCharacter(directoryPath, character);

        // TODO: Remove temporary test print
        System.out.println("Actual result:");
        actualResult.forEach(System.out::println);

        assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetFilesContainingCharacterInEmptyDirectory() throws IOException {
        String directoryPath = Files.createTempDirectory("test").toString();
        char character = '_';

        Exception exception = assertThrows(FileCheckerException.class, () ->
                FileChecker.getFilesContainingCharacter(directoryPath, character));

        String expectedMessage = "The given directory is invalid or does not contain any files.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetFilesContainingCharacterInInvalidDirectory() {
        String directoryPath = "/not/an/actual/path";
        char character = '_';

        Exception exception = assertThrows(FileCheckerException.class, () ->
                FileChecker.getFilesContainingCharacter(directoryPath, character));

        String expectedMessage = "The given directory is invalid or does not contain any files.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
