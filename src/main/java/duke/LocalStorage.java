package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Represents a LocalStorage entity for Duke
 */
public class LocalStorage {
    private static final String HOME_PATH = System.getProperty("user.dir");
    private static Path PATH_NAME;
    private static BufferedReader csvFileReader;
    private static BufferedWriter csvFileWriter;
    private static final String[] CSV_HEADER = {"TaskType", "MarkStatus", "TaskDescription", "TaskDate"};

    protected String fileStoragePath;

    /**
     * Initialise LocalStorage for file storage
     * and sets the fileStoragePath attribute.
     * This function takes in a file path and
     * initialises the reader and writer buffers.
     * If files does not exist, write CSV header into file.
     *
     * @param fileStoragePath The path and file name as String
     */
    public LocalStorage(String fileStoragePath) {
        this.fileStoragePath = fileStoragePath;
        try {
            boolean isFileExist = initializeReaderAndWriter();
            if (!isFileExist) {
                writeCSVHeaderIntoFile();
                csvFileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Oops! An IO exception has occurred at: " + e.getMessage());
        }
    }

    /**
     * Initialize bufferedWriter and bufferedReader
     * for writing and reading of file.
     * This function would check if the file exist with
     * the specified file path.
     * If the file does not exist, create a new file
     * at the specified file path.
     *
     * @return true if file exist, else false.
     */
    private boolean initializeReaderAndWriter() {
        boolean isFileExist = false;
        try {
            PATH_NAME = Paths.get(HOME_PATH, fileStoragePath);
            isFileExist = Files.exists(PATH_NAME);
            // Writer will create new file if file does not exist
            csvFileWriter = Files.newBufferedWriter(PATH_NAME, CREATE);
            csvFileReader = Files.newBufferedReader(PATH_NAME);
        } catch (InvalidPathException e) {
            System.out.println("Oops! It appears that the path to " +
                    "file is not found! Please try again!");
        } catch (IOException e) {
            System.out.println("Oops! IO exception occurred at: " +e.getMessage());
        }

        if (isFileExist) {
            System.out.println("Data file was found!");
            return true;
        } else {
            System.out.println("No present data file was present, " +
                    "a new data file was created at: " + PATH_NAME.toString());
            return false;
        }
    }

    /**
     * Writes the CSV header into specified file.
     * The function would retrieve the predefined CSV_HEADER variable
     * to write into the file.
     * The header would appear at the first row of the file.
     *
     * Assumption: The file is empty.
     *
     * @throws IOException If there were errors with
     *                     writing to the specified file.
     */
    private void writeCSVHeaderIntoFile() throws IOException {
        String csvHeader = String.join(",", CSV_HEADER);
        csvFileWriter.append(csvHeader);
        csvFileWriter.append(System.lineSeparator());
        csvFileWriter.flush();
    }

    /**
     * Returns a list of task that was previously
     * stored in the storage file.
     * The function would retrieve the list of task from the file.
     * The function returns an empty list
     * if no task was retrieved from the file.
     * Else, it would convert the Task retrieve from the file
     * into a Task object and add it to the return list.
     *
     * @return A list of task.
     */
    public ArrayList<Task> getTasksFromFile() {
        ArrayList<Task> listOfStoredTask = new ArrayList<>();
        ArrayList<String[]> listOfTaskString = getListOfTaskStringFromFile();
        // start from 1 to skip header of CSV file
        int taskIndex = 1;
        while (taskIndex < (listOfTaskString.size())) {
            Task newTaskFromFile = getTaskFromListOfTaskString(listOfTaskString.get(taskIndex));
            // If newTaskFromFile was not null, it indicates a valid task.
            if (newTaskFromFile != null) {
                listOfStoredTask.add(newTaskFromFile);
            }
            taskIndex++;
        }
        return listOfStoredTask;
    }

    /**
     * Reads from the file and returns a list task as String[]
     * The function would attempt to read from the file
     * and retrieve each line of String from the file.
     * It would then split the line of String
     * by the ',' delimiter to create a String[]
     * If the file is empty, an empty list is returned.
     *
     * @return A list of task as String[]
     */
    private ArrayList<String[]> getListOfTaskStringFromFile() {
        ArrayList<String[]> listOfStringTask = new ArrayList<>();
        try {
            String currentLine = csvFileReader.readLine();
            while (currentLine != null) {
                String[] stringOutput = currentLine.split(",");
                listOfStringTask.add(stringOutput);
                currentLine = csvFileReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Oops! IO exception occurred at: " +e.getMessage());
        }
        return listOfStringTask;
    }

    /**
     * Converts a String[] that contains a task
     * information into a Task object.
     *
     * @param taskString A task in as String[]
     * @return A Task converted from String[]
     */
    private Task getTaskFromListOfTaskString(String[] taskString) {
        String taskType = taskString[0];
        boolean isMarked = Boolean.parseBoolean(taskString[1]);
        String taskDescription = taskString[2];
        String taskDate = taskString[3];
        LocalDate dateTime = CommandParser.getDateFormat(taskDate);
        Task newTask = createTaskObject(taskType, isMarked, taskDescription, dateTime);
        return newTask;
    }

    /**
     * Creates a Task with the specified taskType,
     * markStatus, taskDescription and taskDate.
     * If taskType is invalid, returns null.
     * Else, create a new Task object and return it.
     *
     * @param taskType The type of Task
     * @param markStatus The status of the Task
     * @param taskDescription The description of the Task
     * @param taskDate The date of the Task
     * @return A Task object created with the specified fields.
     */
    private Task createTaskObject(String taskType, boolean markStatus, String taskDescription, LocalDate taskDate){
        Task newTask = null;
        switch (taskType) {
        case "todo":
            newTask = new Todo(taskDescription, markStatus);
            break;
        case "event":
            newTask = new Event(taskDescription, markStatus, taskDate);
            break;
        case "deadline":
            newTask = new Deadline(taskDescription, markStatus, taskDate);
            break;
        default:
            System.out.println("Oops! It seems that this is a invalid task type!");
            break;
        }
        return newTask;
    }

    /**
     * Converts a Task into a String to be stored into the file.
     * The function would create a String[] as a temporary storage,
     * it would then append the taskType, taskStatus, taskDescription and taskDate
     * into the String[].
     * The function would then join the String[] with ','
     * to return it as a String for file storage.
     *
     * @param taskToBeConverted The Task to be converted into String[]
     * @return A converted Task as String
     */
    private String convertTaskToStringForCSVFile(Task taskToBeConverted) {
        String[] taskDetails = new String[4];
        taskDetails[0] = taskToBeConverted.getTaskType();
        taskDetails[1] = String.valueOf(taskToBeConverted.getStatus());
        taskDetails[2] = taskToBeConverted.getDescription();
        taskDetails[3] = taskToBeConverted.getDateForStorageFile();
        String taskAsString = String.join(",", taskDetails);
        return taskAsString;
    }

    /**
     * Saves the current list of task that the application is tracking.
     * This function is invoked when a user adds, mark, unmark or deletes a task.
     *
     * @param taskList The current list of task in the Application.
     * @throws IOException If there was an error writing to the file.
     */
    public void saveCurrentTaskListToFile(ArrayList<Task> taskList) throws IOException{
        File toBeDeleted = new File(PATH_NAME.toString());
        toBeDeleted.delete();
        csvFileWriter = Files.newBufferedWriter(PATH_NAME, CREATE);
        writeCSVHeaderIntoFile();
        for (Task task : taskList) {
            String convertedTask = convertTaskToStringForCSVFile(task);
            csvFileWriter.write(convertedTask);
            csvFileWriter.append(System.lineSeparator());
            csvFileWriter.flush();
        }
    }
}
