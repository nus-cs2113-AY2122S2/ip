package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static final Scanner scan = new Scanner(System.in);

    //Messages
    public static final String MESSAGE_WELCOME = "Hi there! I'm Domo the chat bot.\nWhat would you like to do?";
    public static final String MESSAGE_BYE = "Goodbye. Hope to see you again soon!";
    public static final String MESSAGE_LIST = "Here is your list so far:";
    public static final String MESSAGE_INSTRUCTION = "Type a valid command (bye, list, mark, unmark, delete, todo, deadline, event) or add a task to your list:";
    public static final String MESSAGE_MARK_SUCCESS = "Congrats! You've completed:\n";
    public static final String MESSAGE_UNMARK_SUCCESS = "Aw, you've marked this as undone:\n";
    public static final String MESSAGE_DELETE_SUCCESS = "Alright, deleting this task:\n";
    public static final String MESSAGE_NO_TASKS = "You don't seem to have any tasks so far!";
    public static final String MESSAGE_FIND_RESULT = "Here are the tasks containing ";
    public static final String MESSAGE_NO_FIND_RESULT = "Sorry, looks like there are no tasks which match";

    //Misc text
    public static final String SEPARATOR = "───────────────────────────────────";

    //Errors
    public static final String ERROR_NO_INPUT = "Hmmmm... you didn't type anything. Please try again using a valid command!";
    public static final String ERROR_IO = "IO Error occurred!";
    public static final String ERROR_NO_DESCRIPTION = "You did not enter a description!";
    public static final String ERROR_NO_CONTENT = "You did not enter a description to find!";
    public static final String ERROR_NOT_VALID_COMMAND = "Sorry, command is not recognised. Please try again using a valid command!";
    public static final String ERROR_INVALID_SYNTAX = "You've entered an invalid syntax for ";
    public static final String ERROR_INVALID_TASK_NUMBER = "Please enter a valid task number!";

    public static void showGreeting() {
        printSeparator();
        System.out.println(MESSAGE_WELCOME);
    }

    /**
     * Continually accepts inputs/commands from the user until the 'bye' command is encountered.
     */
    public static void acceptInputs() {
        String input = getInput();
        while (!Parser.isBye(input)) {
            try {
                Commands.parseCommand(input);
            } catch (DukeException error){
                System.out.println(error.getMessage());
            } catch (IOException e) {
                System.out.println(ERROR_IO);
            }
            input = getInput();
        }
    }

    /**
     * Prepares to exit the program by updating the local file and printing a goodbye message.
     *
     * @throws IOException If IOException occurs
     */
    public static void exit() throws IOException {
        Storage.updateFile();
        printSeparator();
        System.out.println(MESSAGE_BYE);
    }

    /**
     * Gets input from the user.
     *
     * @return user's input
     */
    public static String getInput() {
        System.out.println(MESSAGE_INSTRUCTION);
        return scan.nextLine();
    }

    /**
     * Prints out a line separator for UI aesthetic purposes.
     */
    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }
}

