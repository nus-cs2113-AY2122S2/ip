package solana;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import solana.task.Task;
import solana.command.TodoCommand;
import solana.command.DeadlineCommand;
import solana.command.EventCommand;

import static solana.task.TaskList.tasks;

/**
 * Deals with loading and saving tasks to savedTasks.txt file.
 */
public class Storage {
    public static final int TASK_INDEX = 3;
    public static final int TASK_TYPE_INDEX = 0;
    public static final int STARTING_DESCRIPTION_INDEX = 2;

    public static final int SPLIT_LIMIT = 2;

    public static final int STARTING_LIST_NUMBER = 1;

    public static final int DESCRIPTION_INDEX = 1;

    public static final String UNMARKED_TODO = "[T][";
    public static final String MARKED_TODO = "[T][X]";
    public static final String UNMARKED_DEADLINE = "[D][";
    public static final String MARKED_DEADLINE = "[D][X]";
    public static final String UNMARKED_EVENT = "[E][";
    public static final String MARKED_EVENT = "[E][X]";

    public static final String FOLDER_PATH = "./data/";
    public static final String FILE_PATH = "./data/savedTasks.txt";

    /**
     * Loads a task into TaskList based on its task type. This method makes use of Command objects to execute the
     * addition of tasks to the TaskList.
     *
     * @param taskAsArray String array of task.
     */
    public void parseInputFromFile(String[] taskAsArray) {
        switch(taskAsArray[TASK_TYPE_INDEX]) {
        case UNMARKED_TODO:
            TodoCommand unmarkedTodo = new TodoCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    false, false);
            unmarkedTodo.executeCommand();
            break;
        case MARKED_TODO:
            TodoCommand markedTodo = new TodoCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    false, true);
            markedTodo.executeCommand();
            break;
        case UNMARKED_DEADLINE:
            DeadlineCommand unmarkedDeadline = new DeadlineCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    false, false);
            unmarkedDeadline.executeCommand();
            break;
        case MARKED_DEADLINE:
            DeadlineCommand markedDeadline = new DeadlineCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    false, true);
            markedDeadline.executeCommand();
            break;
        case UNMARKED_EVENT:
            EventCommand unmarkedEvent = new EventCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    false, false);
            unmarkedEvent.executeCommand();
            break;
        case MARKED_EVENT:
            EventCommand markedEvent = new EventCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    false, true);
            markedEvent.executeCommand();
            break;
        default:
            System.out.println("Unable to identify task type!");
        }
    }

    /**
     * Reads saved tasks from savedTasks.txt file, if it exists, and separates the command and description for
     * easy reference. Throws an error if reading fails.
     *
     * @param savedTasks savedTasks.txt file.
     * @throws IOException If reading fails.
     */
    public void readFromFile(File savedTasks) throws IOException {
        Scanner in = new Scanner(savedTasks);
        while (in.hasNext()) {
            String taskAsString = in.nextLine().substring(TASK_INDEX);
            String[] taskAsArray = taskAsString.split(" ", SPLIT_LIMIT);
            parseInputFromFile(taskAsArray);
        }
    }

    /**
     * Loads saved tasks from savedTasks.txt file into TaskList, if file exists. If it does not, this command
     * simply returns.
     */
    public void loadTasks() {
        File savedTasks = new File(FILE_PATH);
        if (!savedTasks.exists()) {
            return;
        }

        try {
            readFromFile(savedTasks);
        } catch (IOException e) {
            System.out.println("Loading tasks from savedTasks.txt failed!");
        }
    }

    /**
     * Writes each task in TaskList to the savedTasks.txt file. Throws an error if writing fails.
     *
     * @throws IOException If writing fails.
     */
    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        int listNumber = STARTING_LIST_NUMBER;

        for (Task task : tasks) {
            fw.write(listNumber + ". ");
            fw.write(task + System.lineSeparator());
            listNumber++;
        }
        fw.close();
    }

    /**
     * Saves the current tasks in TaskList to the savedTasks.txt file, if it exists. If it does not, create the
     * file.
     */
    public void saveTasks() {
        File dataFolder = new File(FOLDER_PATH);
        if (!dataFolder.exists()) {
            boolean isSuccessful = dataFolder.mkdir();
            if (!isSuccessful) {
                System.out.println("Create data folder failed!");
                return;
            }
        }

        File savedTasks = new File(FILE_PATH);
        try {
            savedTasks.createNewFile();
        } catch (IOException e) {
            System.out.println("Create savedTasks.txt failed!");
            return;
        }

        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Writing to savedTasks.txt failed!");
        }
    }
}
