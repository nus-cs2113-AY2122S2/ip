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

    public void readFromFile(File savedTasks) throws FileNotFoundException {
        Scanner in = new Scanner(savedTasks);
        while (in.hasNext()) {
            String taskAsString = in.nextLine().substring(TASK_INDEX);
            String[] taskAsArray = taskAsString.split(" ", SPLIT_LIMIT);
            parseInputFromFile(taskAsArray);
        }
    }

    public void loadTasks() {
        File savedTasks = new File(FILE_PATH);
        if (!savedTasks.exists()) {
            return;
        }

        try {
            readFromFile(savedTasks);
        } catch (FileNotFoundException e) {
            System.out.println("savedTasks.txt not found!");
        }
    }

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
