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
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class LocalStorage {
    private static final String HOME_PATH = System.getProperty("user.dir");
    private static final String FILE_NAME = "localStorage.csv";
    private static Path PATH_NAME;
    private static BufferedReader csvFileReader;
    private static BufferedWriter csvFileWriter;
    private static final String[] CSV_HEADER = {"TaskType", "MarkStatus", "TaskDescription", "TaskDate"};

    public static void initializeLocalFileStorage() {
        try {
            initializeReaderAndWriter();
            writeCSVHeaderIntoFile();
            csvFileWriter.close();
        } catch (IOException e) {
            System.out.println("Oops! An IO exception has occurred at: " + e.getMessage());
        }
    }

    public static ArrayList<Task> getTaskFromFile() {
        ArrayList<Task> listOfStoredTask = new ArrayList<>();
        ArrayList<String[]> storedTask = getTaskStringFromFile();
        // start from 1 to skip header of CSV
        int taskIndex = 1;
        while (taskIndex < (storedTask.size())) {
            Task newTaskFromFile = getTask(storedTask.get(taskIndex));
            if (newTaskFromFile != null) {
                listOfStoredTask.add(newTaskFromFile);
            }
            taskIndex++;
        }
        return listOfStoredTask;
    }

    private static Task getTask(String[] fileInput) {
        String taskType = fileInput[0];
        boolean markStatus = Boolean.parseBoolean(fileInput[1]);
        String taskDescription = fileInput[2];
        String taskDate = fileInput[3];
        Task newTask = createTaskObject(taskType, markStatus, taskDescription, taskDate);
        return newTask;
    }

    private static Task createTaskObject(String taskType, boolean markStatus, String taskDescription, String taskDate){
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

    private static ArrayList<String[]> getTaskStringFromFile() {
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

    private static void writeCSVHeaderIntoFile() throws IOException {
        String csvHeader = String.join(",", CSV_HEADER);
        csvFileWriter.append(csvHeader);
        csvFileWriter.append(System.lineSeparator());
        csvFileWriter.flush();
    }

    private static void initializeReaderAndWriter() {
        try {
            PATH_NAME = Paths.get(HOME_PATH,FILE_NAME);
            // Writer will create new file if file does not exist
            csvFileWriter = Files.newBufferedWriter(PATH_NAME, CREATE);
            csvFileReader = Files.newBufferedReader(PATH_NAME);
        } catch (InvalidPathException e) {
            System.out.println("Oops! It appears that the path to " +
                    "file is not found! Please try again!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (Files.exists(PATH_NAME)) {
            System.out.println("Data file was found!");
        } else {
            System.out.println("No present data file was present, " +
                    "a new data file was created at: " + PATH_NAME.toString());
        }
    }

    private static String convertTaskToStringForCSVFile(Task taskToBeConverted) {
        String[] taskDetails = new String[4];
        taskDetails[0] = taskToBeConverted.getTaskType();
        taskDetails[1] = String.valueOf(taskToBeConverted.getStatus());
        taskDetails[2] = taskToBeConverted.getDescription();
        taskDetails[3] = taskToBeConverted.getDate();
        String taskAsString = String.join(",", taskDetails);
        return taskAsString;
    }

    public static void saveCurrentTaskListToFile(ArrayList<Task> taskList) throws IOException{
        File toBeDeleted = new File(PATH_NAME.toString());
        if (toBeDeleted.delete()) {
            System.out.println("File was deleted successfully!");
        }
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
