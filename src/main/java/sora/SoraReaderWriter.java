package sora;

import tasks.TasksManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SoraReaderWriter {
    private static final String DATA_FILE_PATH = "src/main/java/data/";
    private static final String DATA_FILENAME = "data.txt";
    private static final String FILE_DATA_SEPARATOR = " \\| ";

    private TasksManager tasksManager;

    public SoraReaderWriter(TasksManager tasksManager) {
        this.tasksManager = tasksManager;
    }

    private TasksManager getTasksManager() {
        return this.tasksManager;
    }

    protected String getUserInput() {
        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();
        String userInputTrimmed = userInput.trim();
        return userInputTrimmed;
    }

    protected void loadTaskListFromFile() throws IOException {
        File dataFile = new File(DATA_FILE_PATH + DATA_FILENAME);

        if (!dataFile.exists()) {
            System.out.println("File for storing task list data not found. Creating file...");
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("An error has occurred while trying to create a file.\nHere are the details:");
                System.out.println(e.getMessage());
                // Pass it to caller method to exit
                throw e;
            }

            // Since it's a newly created file, there's no data to read from it. Continue normal execution
            return;
        }

        // Read file data line by line
        Scanner fileReader = new Scanner(dataFile);

        while (fileReader.hasNext()) {
            // Read and parse the line of text from the file
            String rawLineData = fileReader.nextLine();
            String[] parsedLineData = parseFileLineData(rawLineData);

            // Add this line of text data into Sora's task list
            getTasksManager().addTask(parsedLineData);
        }
    }

    private String[] parseFileLineData(String rawLineData) {
        String[] parsedLineData = rawLineData.split(FILE_DATA_SEPARATOR);
        return parsedLineData;
    }
}
