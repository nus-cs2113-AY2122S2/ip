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

/**
 * This class handles the reading and writing of Sora's task data from and to a designated file on the user's
 * system.
 * <p>
 * If the user does not have the specified file in the designated location on his/her system, it will be
 * automatically created when Sora is run.
 */
public class SoraStorage {
    // Default directory and file names for data storage
    private static final String USER_HOME_PROPERTY = "user.home";
    private static final String DATA_DIRECTORY = "CS2113T_iP_Sora_Resources";
    private static final String DATA_FILENAME = "data.txt";

    // Default delimiters for processing file data
    private static final String FILE_DATA_SEPARATOR_REGEX = " \\| ";
    private static final String FILE_DATA_SEPARATOR = " | ";

    public static final String TODO_TYPE_FILE_ABBREVIATION = "T";
    public static final String EVENT_TYPE_FILE_ABBREVIATION = "E";
    public static final String DEADLINE_TYPE_FILE_ABBREVIATION = "D";

    private SoraUI soraUI;
    private String homeDir;
    private Path directoryPath;
    private Path filePath;

    /**
     * Constructs an instance of SoraStorage and setting the default directory and file paths.
     */
    public SoraStorage() {
        this.soraUI = new SoraUI();

        // Get user system's home directory
        this.homeDir = System.getProperty(USER_HOME_PROPERTY);
        this.directoryPath = Paths.get(this.homeDir, DATA_DIRECTORY);
        this.filePath = Paths.get(this.homeDir, DATA_DIRECTORY, DATA_FILENAME);
    }

    /**
     * Gets the directory path of the file storage. This does not include the filename
     *
     * @return A Path object containing the directory path of the file storage.
     */
    public Path getDirectoryPath() {
        return this.directoryPath;
    }

    /**
     * Gets the full file path of the file storage.
     *
     * @return A Path object containing the full file path of the file storage.
     */
    public Path getFilePath() {
        return this.filePath;
    }

    /**
     * Reads the tasks' data that are stored in the designated file on the user's system and adds them to
     * this running instance of Sora's list.
     *
     * @param taskList An instance of taskList to add the file's task data to.
     * @throws IOException If this method fails to create a directory and/or file (whenever necessary).
     */
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

    /**
     * Checks if the specified file containing the stored task data already exists on the user's system before
     * this running instance of Sora. If it does not exist, this method will create the file in the default location.
     *
     * @param filePath The path to the file that contains the stored tasks' data.
     * @param directoryAlreadyExists Indicates if the directory has already existed before this running instance
     *                               of Sora.
     * @return true if the file already exists prior to this running instance of Sora. Otherwise, false is returned.
     * @throws IOException If this method fails to create the file that is required to store the task list data.
     */
    private boolean checkAndCreateDataFile(Path filePath, boolean directoryAlreadyExists) throws IOException {
        if (Files.exists(filePath)) {
            // Data file already exists
            return true;
        }

        // Parts of Sora's UI response will change depending on whether the directory already exists
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

    /**
     * Checks if the specified directory that contains the stored task data already exists on the user's
     * system before this running instance of Sora. If it does not exist, this method will create the directory
     * in the default location.
     *
     * @param directoryPath The path to the directory that have the file that contains the stored tasks' data.
     * @return true if the directory already exists prior to this running instance of Sora. Otherwise, false is
     * returned.
     * @throws IOException If this method fails to create the directory that is required to contain the required file.
     */
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

    /**
     * Takes in a line read from the file containing the stored tasks' data and divides the line into the
     * various task details based on the specified delimiter.
     *
     * @param rawLineData The line of data to be parsed.
     * @return A string array containing the parsed line of data.
     */
    private String[] parseFileLineData(String rawLineData) {
        String[] parsedLineData = rawLineData.split(FILE_DATA_SEPARATOR_REGEX);
        return parsedLineData;
    }

    /**
     * Takes in a newly-created Task instance, parses the task's data for exporting to the data file, and
     * appends it to the end of the data file.
     * <p>
     * Though this method is used to write a newly-created Task instance, it can take in any instance of Task.
     *
     * @param newTask The Task instance whose data will be written to the end of the data file.
     * @throws IOException If this method is unable to open the specified data file.
     */
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

    /**
     * Parses the data in the Task instance to a format that will be written to the data file.
     *
     * @param newTask The Task instance whose data will be parsed.
     * @return A string that is formatted for exporting to the data file.
     */
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

    /**
     * Returns the abbreviated letter of the task type.
     *
     * @param task The Task instance.
     * @return "T" if the Task instance is of Todo type, "E" if the Task instance is of Event type, or
     * "D" if the Task instance is of Deadline type.
     */
    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return TODO_TYPE_FILE_ABBREVIATION;
        } else if (task instanceof Event) {
            return EVENT_TYPE_FILE_ABBREVIATION;
        } else if (task instanceof Deadline) {
            return DEADLINE_TYPE_FILE_ABBREVIATION;
        }
        return "";
    }

    /**
     * Returns the date (and time) specified in the Task instance.
     *
     * @param task The task instance.
     * @return A string containing the date (and time) specified in the Task instance.
     */
    private String getDate(Task task) {
        if (task instanceof Event) {
            return ((Event) task).getEventPeriod();
        } else if (task instanceof Deadline) {
            return ((Deadline) task).getDueDate();
        }
        return "";
    }

    /**
     * Overwrites the data file with an updated task list. Currently, it is used when a task is marked
     * or unmarked as done, or when a task is deleted.
     *
     * @param taskList An instance of TaskList that contains the updated task list.
     * @throws IOException If the required file for writing the tasks' data to cannot be opened.
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
