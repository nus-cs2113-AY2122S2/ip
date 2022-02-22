package sora;

import tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class SoraStorage {
    private static final String USER_HOME_PROPERTY = "user.home";
    private static final String DATA_DIRECTORY = "CS2113T_iP_Sora_Resources";
    private static final String DATA_FILENAME = "data.txt";

    private static final String FILE_DATA_SEPARATOR_REGEX = " \\| ";
    private static final String FILE_DATA_SEPARATOR = " | ";

    public static final String TODO_TYPE_FILE_ABBREVIATION = "T";
    public static final String EVENT_TYPE_FILE_ABBREVIATION = "E";
    public static final String DEADLINE_TYPE_FILE_ABBREVIATION = "D";

    private SoraUI soraUI;


    private String homeDir;
    private Path directoryPath;
    private Path filePath;

    public SoraStorage() {
        this.soraUI = new SoraUI();

        // Get user system's home directory
        this.homeDir = System.getProperty(USER_HOME_PROPERTY);
        this.directoryPath = Paths.get(this.homeDir, DATA_DIRECTORY);
        this.filePath = Paths.get(this.homeDir, DATA_DIRECTORY, DATA_FILENAME);
    }

    public Path getDirectoryPath() {
        return this.directoryPath;
    }

    public Path getFilePath() {
        return this.filePath;
    }

    protected void loadTaskListFromFile(TaskList taskList) throws IOException {
        // Check if required directory and file exist
        boolean directoryAlreadyExists = checkAndCreateDataDirectory(getDirectoryPath());
        boolean fileAlreadyExists = checkAndCreateDataFile(getFilePath(), directoryAlreadyExists);

        if (!fileAlreadyExists) {
            // Since the file has just been created, there is no data to read. Exit method immediately.
            return;
        }

        // Open the file for reading
        File dataFile = new File(getFilePath().toUri());

        // Read file data line by line
        Scanner fileReader = new Scanner(dataFile);

        while (fileReader.hasNext()) {
            // Read and parse the line of text from the file
            String rawLineData = fileReader.nextLine();
            String[] parsedLineData = parseFileLineData(rawLineData);

            // Add this line of text data into Sora's task list
            taskList.addTask(parsedLineData);
        }

        soraUI.printLoadedFileDataResponse();
    }

    private boolean checkAndCreateDataFile(Path filePath, boolean directoryAlreadyExists) throws IOException {
        if (Files.exists(filePath)) {
            // Data file already exists
            return true;
        }

        soraUI.printFileNotFound(directoryAlreadyExists);

        try {
            Files.createFile(filePath);
            soraUI.printFileCreatedResponse(filePath);
        } catch (IOException e) {
            System.out.println("An error has occurred while trying to create a file.\nHere are the details:");
            System.out.println(e.getMessage());
            // Pass it to caller method to exit
            throw e;
        }

        return false;
    }

    private boolean checkAndCreateDataDirectory(Path directoryPath) throws IOException {
        if (Files.exists(directoryPath)) {
            // Directory already exists. Exit method
            return true;
        }

        // Directory does not exist. Create new directory
        soraUI.printDirectoryNotFound();

        try {
            Files.createDirectory(directoryPath);
        } catch (IOException e) {
            System.out.println("Error: Could not create directory. Here are the details:");
            System.out.println(e.getMessage());
            throw e;
        }

        return false;
    }

    private String[] parseFileLineData(String rawLineData) {
        String[] parsedLineData = rawLineData.split(FILE_DATA_SEPARATOR_REGEX);
        return parsedLineData;
    }

    public void writeNewTaskToFile(Task newTask) throws IOException {
        String taskInFileFormat = buildTaskTextForFile(newTask);

        // Add line of text to end of file
        try {
            FileWriter fileWriter = new FileWriter(filePath.toString(), true);
            fileWriter.append(taskInFileFormat + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error opening data file for writing. Here are some details:");
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private String buildTaskTextForFile(Task newTask) {
        // Build line of text
        StringBuilder taskInFileFormat = new StringBuilder();
        String taskType = getTaskType(newTask);
        taskInFileFormat.append(taskType);
        taskInFileFormat.append(FILE_DATA_SEPARATOR);
        String isDoneValue = newTask.isDone() ? "1" : "0";
        taskInFileFormat.append(isDoneValue);
        taskInFileFormat.append(FILE_DATA_SEPARATOR);
        taskInFileFormat.append(newTask.getDescription());

        if (!taskType.equals(TODO_TYPE_FILE_ABBREVIATION)) {
            // Add date value into taskInFileFormat
            taskInFileFormat.append(FILE_DATA_SEPARATOR);
            taskInFileFormat.append(getDate(newTask));
        }
        return taskInFileFormat.toString();
    }

    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return TODO_TYPE_FILE_ABBREVIATION;
        } else if (task instanceof Event) {
            return EVENT_TYPE_FILE_ABBREVIATION;
        } else if (task instanceof Deadline) {
            return DEADLINE_TYPE_FILE_ABBREVIATION;
        }

        // TODO: Implement exception?
        return "";
    }

    private String getDate(Task task) {
        if (task instanceof Event) {
            return ((Event) task).getEventPeriodForFileStorage();
        } else if (task instanceof Deadline) {
            return ((Deadline) task).getDueDateForFileStorage();
        }

        // TODO: Implement exception?
        return "";
    }

    /**
     * (WIP Documentation) Overwrites the data file with an updated task list. Currently used when a task is marked
     * or unmarked as done, or when a task is deleted.
     *
     * I hope to implement a more efficient version, that is, search for the task to be updated
     * in the file and updated it directly.
     *
     * @param taskList
     */
    public void rewriteAllTasksToFile(TaskList taskList) throws IOException {
        ArrayList<Task> tasksList = taskList.getList();

        try {
            FileWriter fileWriter = new FileWriter(filePath.toString());

            for (Task task  : tasksList) {
                String taskText = buildTaskTextForFile(task);
                fileWriter.append(taskText + System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error opening data file for writing. Here are the details:");
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
