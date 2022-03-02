import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class handles with the reading and writing of data from and onto
 * a file for storing the tasks a user input during a run of the program.
 */
public class Storage {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "Duke.txt";

    /**
     * This function sets up the storage of all the tasks into the TaskList.
     * If the directory or file does not exist, it will create one.
     * If it does exist, then this will read from the file the relevant data.
     *
     * @param tasks The TaskList to store the tasks
     */
    public static void storageSetup(TaskList tasks) {
        File directory = new File(DIRECTORY_NAME);
        File file = new File(DIRECTORY_NAME + "/" + FILE_NAME);
        if (!directory.exists()) {
            createDirectory(directory);
            createFile(file);
        } else if (!file.exists()) {
            createFile(file);
        }
        try {
            readTasksFromFile(file, tasks);
        } catch (FileNotFoundException e) {
            // Workaround for now until proper ui class is created
            Ui.printTextBetweenLines("\t File not found!");
        }
    }

    /**
     * Creates the "data" directory.
     *
     * @param directory The "data" directory
     */
    public static void createDirectory(File directory) {
        try {
            directory.mkdir();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Creates the "duke.txt" file for storing the tasks.
     *
     * @param file The "duke.txt" file
     */
    public static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * This function reads tasks from the file and loads it onto the TaskList tasks.
     *
     * @param file The "duke.txt" file
     * @param tasks The TaskList to store all tasks
     * @throws FileNotFoundException
     */
    public static void readTasksFromFile(File file, TaskList tasks) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            loadTaskFromFile(tasks, scanner.nextLine());
        }
    }

    /**
     * This function loads the tasks onto the TaskList.
     * It creates the correct instance of task based on the first character it encounters.
     *
     * @param tasks The TaskList to store all tasks
     * @param line The line of data read from the file
     */
    public static void loadTaskFromFile(TaskList tasks, String line) {
        String[] content = line.split("\\|");
        boolean isCompleted = ("1".equals(content[1]));
        switch(content[0]) {
        case "T":
            String taskDescription = content[2];
            tasks.add(new Todo(taskDescription, isCompleted));
            break;
        case "D":
            taskDescription = content[2];
            String deadlineBy = content[3];
            tasks.add(new Deadline(taskDescription, isCompleted, deadlineBy));
            break;
        case "E":
            taskDescription = content[2];
            String eventAt = content[3];
            tasks.add(new Event(taskDescription, isCompleted, eventAt));
            break;
        default:
            break;
        }
    }

    /**
     * This function writes tasks created while running onto the correct file
     * with the correct format.
     *
     * @param tasks The TaskList storing all the tasks to be written onto a file
     */
    public static void writeTasksToFile(TaskList tasks) {
        try {
            FileWriter file = new FileWriter(DIRECTORY_NAME + "/" + FILE_NAME);
            for (int i = 0; i < tasks.size(); i++) {
                file.write(tasks.get(i).getTaskCode() + "|" + (tasks.get(i).getCompletionStatus() ? "1" : "0")
                        + "|" + tasks.get(i).getTaskDescription() + "|" + tasks.get(i).getExtraInfo() + "\n");
            }
            file.close();
        } catch (IOException e) {
            Ui.printTextBetweenLines("\t File path error! Check again!");
        }
    }
}
