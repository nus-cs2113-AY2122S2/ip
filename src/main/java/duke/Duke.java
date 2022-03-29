package duke;

import duke.parser.Parser;
import duke.taskmanagement.TaskManager;
import duke.userinterface.UserInterface;

/**
 * Duke is the main class containing the initial entry point for the entire program.
 */
public class Duke {
    /**
     * Main method will instantiate UserInterface for text printing. Parser will take in a TaskManager
     * object and interact with each other as the program runs.
     * @param args
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        Parser parser = new Parser();
        TaskManager tasks = new TaskManager();
        ui.printGreeting();
        parser.run(tasks);
    }
}