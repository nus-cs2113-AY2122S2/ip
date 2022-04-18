package duke.storage;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.exception.DataStorageAccessException;
import duke.exception.MalformedTaskFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.common.Strings.*;

/**
 * Represents the data file in which tasks are stored.
 */
public class DataStorage {
    public static final String DEFAULT_DIRECTORY = "./data/";
    public static final String DEFAULT_FILENAME = "duke.txt";

    public final File directory;
    public final File dataFile;
    private ArrayList<String> dataLoadFeedback;

    public DataStorage() {
        this(DEFAULT_DIRECTORY, DEFAULT_FILENAME);
    }

    public DataStorage(String directory, String filename) {
        this.directory = new File(DEFAULT_DIRECTORY);
        dataFile = new File(DEFAULT_DIRECTORY + DEFAULT_FILENAME);
        dataLoadFeedback = new ArrayList<>();
    }

    /**
     * Checks if the data directory exists.
     * @return true if the directory already exists, or false otherwise.
     */
    public boolean checkDirectoryExists() {
        return directory.exists();
    }

    public void createDirectory() throws DataStorageAccessException {
        if (!directory.mkdir()) {
            throw new DataStorageAccessException();
        }
    }

    /**
     * Checks if the data file exists.
     * @return true if the file already exists, or false otherwise.
     */
    public boolean checkFileExists() {
        return dataFile.exists();
    }

    /**
     * Creates the data file.
     * @throws IOException
     */
    public void createFile() throws IOException {
        dataFile.createNewFile();
    }

    /**
     * Loads data from the data file.
     * Feedback messages (including malformed tasks and other errors) are stored in dataLoadFeedback.
     * @return a TaskList containing all tasks successfully parsed from the file.
     * @throws FileNotFoundException
     */
    public TaskList load() throws FileNotFoundException {
        TaskList tasks = new TaskList();
        Scanner sc = new Scanner(dataFile);
        int lineNum = 1;
        while (sc.hasNext()) {
            Task t = parseSavedTask(sc.nextLine(), lineNum);
            if (t != null) {
                tasks.addTask(t);
            }
            lineNum++;
        }
        dataLoadFeedback.add(String.format(MESSAGE_DATA_LOADED, tasks.getNumTasks()));
        return tasks;
    }

    /**
     * Parse task saved as string format and convert it back into an object.
     * @param taskAsString task saved as string
     * @param lineNum line number
     * @return Task object if successful, or null if error
     */
    private Task parseSavedTask(String taskAsString, int lineNum) {
        String[] processedString = taskAsString.split(FS);
        try {
            final String taskType = processedString[0];
            final boolean taskMarked;
            if (processedString[1].equals("1")) {
                taskMarked = true;
            } else if (processedString[1].equals("0")) {
                taskMarked = false;
            } else {
                throw new MalformedTaskFormatException();
            }
            final String taskDescription = processedString[2];
            switch (taskType) {
            case "T":
                if (processedString.length != 3) {
                    throw new MalformedTaskFormatException();
                }
                Todo todo = new Todo(taskDescription);
                todo.setIsDone(taskMarked);
                return todo;
            case "D":
                if (processedString.length != 4) {
                    throw new MalformedTaskFormatException();
                }
                final String dueBy = processedString[3];
                Deadline deadline = new Deadline(taskDescription, dueBy);
                deadline.setIsDone(taskMarked);
                return deadline;
            case "E":
                if (processedString.length != 4) {
                    throw new MalformedTaskFormatException();
                }
                final String time = processedString[3];
                Event event = new Event(taskDescription, time);
                event.setIsDone(taskMarked);
                return event;
            default:
                throw new MalformedTaskFormatException();
            }
        } catch (IndexOutOfBoundsException | MalformedTaskFormatException e) {
            // Using System.out.println here because I don't want to print a horizontal line after each output
            dataLoadFeedback.add(String.format(MESSAGE_MALFORMED_TASK, lineNum, taskAsString));
            return null;
        }
    }

    public ArrayList<String> getDataLoadFeedback() {
        return dataLoadFeedback;
    }

    /**
     * Writes data to the data file.
     * @param tasks TaskList containing the tasks to be saved.
     * @throws IOException
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(dataFile);
        for (Task t : tasks.getTaskList()) {
            fw.write(t.formatAsData(FS) + LS);
        }
        fw.close();
    }
}
