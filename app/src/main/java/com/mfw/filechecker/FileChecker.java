package com.mfw.filechecker;

import com.mfw.filechecker.exception.FileCheckerException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing file checking functionalities.
 */
public class FileChecker {
    /**
     * Gets a list of all files that contain the given character from the given directory path.
     * @param directoryPath of the folder where the files should be inspected as {@code String}
     * @param character to filter the files by as {@code char}
     * @return list of filtered files by character as {@code List<String>}
     * @throws FileCheckerException if the given directory is invalid or does not contain any files
     */
    public static List<String> getFilesContainingCharacter(String directoryPath, char character)
            throws FileCheckerException {
        List<String> filesContainingCharacter = new ArrayList<>();
        File directory = new File(directoryPath);

        // Get all files from the directory
        File[] files = directory.listFiles();

        // Make sure that the directory is valid and contains files
        if (files == null || files.length == 0) {
            throw new FileCheckerException("The given directory is invalid or does not contain any files.");
        }

        // Add the name of each file that matches the character to
        for (File file : files) {
            if (file.isFile() && file.getName().contains(String.valueOf(character))) {
                filesContainingCharacter.add(file.getName());
            }
        }

        if (filesContainingCharacter.isEmpty()) {
            throw new FileCheckerException("There are no files that contain the given character.");
        }

        return filesContainingCharacter;
    }
}
