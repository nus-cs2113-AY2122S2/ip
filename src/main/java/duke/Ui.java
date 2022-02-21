package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ui {
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    private Scanner uiScan;

    public Ui() {
        this.uiScan = new Scanner(System.in);
    }

    /**
     * Reads commands from stdin and executes them using commandExec(commandInput)
     * until the user inputs COMMAND_EXIT, upon which the function returns.
     */
    public void loopCommandInput() {
        String commandInput;
        do {
            commandInput = uiScan.nextLine();
            try {
                executeCommand(commandInput);
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!commandInput.equals(COMMAND_EXIT));
    }

    /**
     * Checks the String inputted by the user and executes the appropriate command
     * using a switch statement.
     *
     * @param nextLine The command to be executed.
     */
    private void executeCommand(String nextLine) throws DukeException {
        ArrayList<String> pieces = new ArrayList<>(Arrays.asList(nextLine.split(" ")));
        String commandType = pieces.get(0);
        switch (commandType) {
        case COMMAND_EXIT:
            printGoodbye();
            break;
        case COMMAND_LIST:
            TaskList.listTasks();
            break;
        case COMMAND_MARK:
            String taskToMark = pieces.get(1);
            TaskList.doTask(taskToMark);
            break;
        case COMMAND_UNMARK:
            String taskToUnmark = pieces.get(1);
            TaskList.undoTask(taskToUnmark);
            break;
        case COMMAND_DELETE:
            String taskToDelete = pieces.get(1);
            TaskList.deleteTask(this, taskToDelete);
            break;
        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            TaskList.addTask(pieces);
            break;
        default:
            throw new DukeException("Command not found.");
        }
    }


    /**
     * Prints a goodbye message and returns.
     */
    private void printGoodbye() {
        printDivider();
        System.out.println("Goodbye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Prints 37 underscores to stdout to serve as a divider.
     */
    private void printDivider() {
        System.out.println("_____________________________________");
    }

    /**
     * Prints a greeting with divider lines.
     */
    public void printGreeting() {
        printDivider();
        System.out.println("Hello! I'm Michel.");
        System.out.println("What can I do for you?");
        printDivider();
    }
}
