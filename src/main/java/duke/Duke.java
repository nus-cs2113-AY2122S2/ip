package duke;

import command.Command;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Where the Duke application starts
 * Initialises the file directory, and objects required for Duke
 * Presents UI to user
 */

public class Duke {
    /** File and directory path of where data file is to be stored. */
    public static final String FILE_PATH = "data/tasks.txt";
    public static final String DIRECTORY_PATH = "data";

    private static TaskList taskList = new TaskList();
    private static ArrayList<Task> tasks = taskList.getTasks();
    private static int taskCount = 0;
    public static Ui ui = new Ui();
    public static Storage storage = new Storage(FILE_PATH, DIRECTORY_PATH, taskList);

    public static void main(String[] args) {
        storage.loadTasks();
        ui.showWelcome();

        String userInput, message;
        Scanner in = new Scanner(System.in);
        /** Do while loop to ask for user input */
        do{
            userInput = in.nextLine();
            Command command = new Parser().parseCommand(userInput, taskList);
            command.execute();
            storage.saveTasks();
        }while(!userInput.equals("bye"));

        ui.showGoodbye();
    }


}
