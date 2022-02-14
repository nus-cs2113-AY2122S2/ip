package sora;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SoraIOHandler {
    private static final String DEFAULT_DATA_FILE_PATH = "src/main/java/data/";
    private static final String DEFAULT_DATA_FILENAME = "data.txt";

    protected String getUserInput() {
        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();
        String userInputTrimmed = userInput.trim();
        return userInputTrimmed;
    }

    protected void loadTaskListFromFile() throws IOException {
        File dataFile = new File(DEFAULT_DATA_FILE_PATH + DEFAULT_DATA_FILENAME);

        if (!dataFile.exists()) {
            System.out.println("File for storing task list data not found. Creating file...");
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                // Pass it to caller method to handle
                throw e;
            }
        }
    }
}
