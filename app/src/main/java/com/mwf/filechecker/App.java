package com.mwf.filechecker;

import com.mwf.filechecker.exception.FileCheckerException;
import com.mwf.filechecker.exception.InvalidInputException;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the application.
 */
public class App {
    /**
     * Application entry point and output handler.
     * @param args the command line arguments as {@code String[]}
     **/
    public static void main(String[] args) {
        System.out.println("Welcome to File Checker.");
        System.out.println("You need to provide some details before we get started.");
        System.out.println();

        System.out.println("First we need the path of the directory where the files should be inspected.");
        System.out.println("For example, on Windows this could be 'C:\\Users\\<username>\\Documents'.");
        System.out.print("Directory path: ");
        String path = promptValidPathInput();

        System.out.println("We also need a character to filter the files by.");
        System.out.println("For example, list all files containing an '_'.");
        System.out.print("Character: ");
        char character = promptValidCharacterInput();

        System.out.println();
        printFilesContainingCharacter(path, character);

        System.out.println();
        System.out.println("Thank you for using File Checker.");
        System.out.println("Press the 'Enter' key to exit the application.");
        readString();
    }

    /**
     * Prints all files that contain the given character inside the folder of the given path.
     * @param path of the folder where the files should be inspected as {@code String}
     * @param character to filter the files by as {@code char}
     */
    public static void printFilesContainingCharacter(String path, char character) {
        try {
            List<String> foundFiles = FileChecker.getFilesContainingCharacter(path, character);

            System.out.println("All files containing '" + character + "':");

            for (String file : foundFiles) {
                System.out.println("• " + file);
            }
        } catch (FileCheckerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prompts the user to input a valid directory path until a valid directory path is provided.
     * @return valid directory path as {@code String}
     */
    public static String promptValidPathInput() {
        while (true) {
            try {
                return readPathInput();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                System.out.print("Please provide a valid directory path: ");
            }
        }
    }

    /**
     * Reads the user-provided input and validates its format as a valid directory path.
     * @return valid user-provided path as {@code String}
     * @throws InvalidInputException if the user-provided input is invalid
     */
    public static String readPathInput() throws InvalidInputException {
        String input = readString();

        // Check if the input is a valid path and if the directory exists
        if (isInvalidDirectoryPath(input)) {
            throw new InvalidInputException("Invalid input, the provided path is not valid.");
        }

        return input;
    }

    /**
     * Prompts the user to input a character until a valid character is provided.
     * @return valid character as {@code char}
     */
    private static char promptValidCharacterInput() {
        while (true) {
            try {
                return readCharacterInput();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                System.out.print("Please provide a single character: ");
            }
        }
    }

    /**
     * Reads the user-provided input and validates its format as a single character.
     * @return valid user-provided character as {@code char}
     * @throws InvalidInputException if the user-provided input is invalid
     */
    public static char readCharacterInput() throws InvalidInputException {
        String input = readString();

        // Check if the input is empty or contains more than one character
        if (input.length() != 1 || input.isBlank()) {
            throw new InvalidInputException("Invalid input, the provided input is not a single character.");
        }

        return input.charAt(0);
    }

    private static boolean isInvalidDirectoryPath(String path) {
        Path directoryPath;

        // Attempt to convert the given path to a path object; the path is invalid otherwise
        try {
            directoryPath = Paths.get(path);
        } catch (InvalidPathException e) {
            return true;
        }

        // Check if the converted path leads to an existing directory
        return !Files.isDirectory(directoryPath);
    }

    private static String readString() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        return scanner.nextLine();
    }
}
