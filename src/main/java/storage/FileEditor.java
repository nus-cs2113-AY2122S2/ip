package storage;

import exception.DukeException;
import tasks.*;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * Loads task list from the user's task file in the user's hard disk and saves any changes to the file
 */
public class FileEditor {
    private static String directoryName;
    private static String fileName;
    private File userFile;
    private File userDirectory;

    /**
     * Creates a new directory or text file if the file/directory does not yet exist
     *
     * @param fileName tasks.txt
     * @directoryName directoryName data
     */
    public FileEditor(String fileName, String directoryName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        try {
            userDirectory = new File(directoryName);
            if (!userDirectory.exists()) {
                userDirectory.mkdir();
                System.out.println(Ui.MISSING_FILE_DIRECTORY_MESSAGE);
            }
            userFile = new File(directoryName + File.separator + fileName);
            if (!userFile.exists()) {
                userFile.createNewFile();
                System.out.println(Ui.MISSING_FILE_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rewrites the task list file with the latest tasks
     *
     * @param tasks ArrayList of tasks from TaskManager.tasks
     * @throws IOException If error is encountered when reading or writing to file
     * */
    public static void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(directoryName + File.separator + fileName);
        for (Task task : tasks) {
            String taskToAppend = task.getDetails();
            fw.write(taskToAppend + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Reads the task list file from the user's hard disk and creates an ArrayList of tasks from it
     *
     * @throws IOException If error is encountered when reading or writing to file
     * @return ArrayList of tasks as specified by the user's task list file
     * */
    public ArrayList<Task> readFileContents() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(userFile);
            ArrayList<String> tasksFromFile = new ArrayList<>();
            while (s.hasNextLine()) {
                tasksFromFile.add(s.nextLine());
            }
            tasks = storeAsTasks(tasksFromFile);
        } catch (IOException | DateTimeException | DukeException e) {
            System.out.println(Ui.CORRUPT_FILE_MESSAGE);
            new FileWriter("./data/tasks.txt", false).close();
        }
        return tasks;
    }

    /**
     * Converts the ArrayList of task strings read from the user's task list file into an ArrayList of tasks
     *
     * @return ArrayList of tasks
     * */
    private static ArrayList<Task> storeAsTasks(ArrayList<String> taskStrings) throws DateTimeException, DukeException {
        ArrayList<Task> tasklist = new ArrayList<>();
        for (String taskString : taskStrings) {
            char taskType = taskString.charAt(0);
            Task task = null;
            switch (taskType) {
            case 'T':
                task = new Todo(extractDescriptionFromTaskString(taskString));
                break;
            case 'D':
                task = new Deadline(extractDescriptionFromTaskString(taskString), extractDateTimeFromTaskString(taskString));
                break;
            case 'E':
                task = new Event(extractDescriptionFromTaskString(taskString), extractDurationFromTaskString(taskString));
                break;
            default:
                throw new DukeException();
            }
            task.setDone(extractDoneStatusFromTaskString(taskString));
            tasklist.add(task);
        }
        return tasklist;
    }

    /**
     * Extracts task description from each string in the ArrayList of task strings read from the user's task list file
     *
     * @return Substring representing a task description
     * */
    private static String extractDescriptionFromTaskString(String taskString) {
        String taskDescription;
        int startIndexOfDescription = taskString.indexOf("|", taskString.indexOf("|") + 1)+1;
        int endIndexOfDescription = taskString.indexOf("|", startIndexOfDescription);
        if (endIndexOfDescription == -1) {
            taskDescription = taskString.substring(startIndexOfDescription);
        } else {
            taskDescription = taskString.substring(startIndexOfDescription, endIndexOfDescription);
        }
        return taskDescription;
    }

    /**
     * Extracts the done status of a task from each string in the ArrayList of task strings read from the user's task list file
     *
     * @return Boolean representing whether a task has been done or not
     * */
    private static Boolean extractDoneStatusFromTaskString(String taskString) {
        int startIndex = taskString.indexOf("|")+1;
        char doneStatus = taskString.charAt(startIndex);
        if (doneStatus == '1') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Extracts the deadline of a deadline task from each deadline task string in the ArrayList of task strings read from the user's task list file
     *
     * @return Date and time of the deadline of a deadline task
     * */
    private static LocalDateTime extractDateTimeFromTaskString(String taskString) throws DateTimeException {
        int startIndexOfDescription = taskString.indexOf("|", taskString.indexOf("|") + 1)+1;
        int startIndexOfTime = taskString.indexOf("|", startIndexOfDescription)+1;
        String dateTimeString = taskString.substring(startIndexOfTime);
        LocalDateTime dateTime = null;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        dateTime = LocalDateTime.from(f.parse(dateTimeString));
        return dateTime;
    }

    /**
     * Extracts the duration of an event task from each event task string in the ArrayList of task strings read from the user's task list file
     *
     * @return Duration of the event task
     * */
    private static String extractDurationFromTaskString(String taskString) {
        int startIndexOfDescription = taskString.indexOf("|", taskString.indexOf("|") + 1)+1;
        int startIndexOfTime = taskString.indexOf("|", startIndexOfDescription)+1;
        String duration = taskString.substring(startIndexOfTime);
        return duration;
    }

}
