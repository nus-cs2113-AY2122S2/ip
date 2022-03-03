package Duke.Ui;

import Duke.DukeException;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    public static final String DISPLAY_LINE = "----------------------------------------------------";
    private static Scanner in;
    protected static final int LISTSIZE = 1;

    /**
     * Constructs a user interface object.
     */
    public Ui () {
        this.in = new Scanner(System.in);
    }

    /**
     * Displays an example of the correct 'mark' command to users.
     *
     * @param taskList The task object that contains the tasks.
     * @return The message to be displayed.
     */
    public static String displayMarkMessage(TaskList taskList) {
        String checkGrammer = "items";
        if (taskList.size() == LISTSIZE) {
            checkGrammer = "item";
        }
        return DISPLAY_LINE + System.lineSeparator() + "There are " + taskList.size() + " "
                + checkGrammer + " in the list\n"
                + "Please input which item you would like to mark.\n"
                + "For eg. 'mark 2' \n"+ DISPLAY_LINE;
    }

    /**
     * Displays an example of the correct 'unmark' command to users.
     *
     * @param taskList The task object that contains the tasks.
     * @return The message to be displayed.
     */
    public static String displayUnmarkMessage(TaskList taskList) {
        String checkGrammer = "items";
        if (taskList.size() == LISTSIZE) {
            checkGrammer = "item";
        }
        return DISPLAY_LINE + System.lineSeparator() + "There are " + taskList.size() + " "
                + checkGrammer + " in the list\n"
                + "Please input which item you would like to unmark.\n"
                + "For eg. 'unmark 2' \n"+ DISPLAY_LINE;
    }

    /**
     * Displays an example of the correct 'delete' command to users.
     *
     * @param taskList The task object that contains the tasks.
     * @return The message to be displayed.
     */
    public static String displayDeleteMessage(TaskList taskList) {
        String checkGrammer = "items";
        if (taskList.size() == LISTSIZE) {
            checkGrammer = "item";
        }
        return DISPLAY_LINE + System.lineSeparator()+ "There are " + taskList.size() + " "
                + checkGrammer + " in the list\n"
                + "Please input which item you would like to delete.\n"
                + "For eg. 'delete 2' \n"+ DISPLAY_LINE;
    }

    /**
     * Informs the user that the list is empty.
     *
     * @return The message to be displayed.
     */
    public static String displayListMessage() {
        return DISPLAY_LINE + System.lineSeparator() + "There are no items in the list, please add something!:)\n"
                + DISPLAY_LINE;
    }

    /**
     * Displays an example of the correct 'todo' command to users.
     *
     * @return The message to be displayed.
     */
    public static String displayTodoMessage() {
        return DISPLAY_LINE + System.lineSeparator() + "Please input a description\n" +
                "For eg. 'todo borrow book'\n" + DISPLAY_LINE;
    }

    /**
     * Displays an example of the correct 'deadline' command to users.
     *
     * @return The message to be displayed.
     */
    public static String displayDeadlineMessage() {
        return DISPLAY_LINE + System.lineSeparator() + "Please input a description, '/by', date\n" +
                "For eg. 'deadline work /by Sunday'\n" + DISPLAY_LINE;
    }

    /**
     * Displays an example of the correct 'event' command to users.
     *
     * @return The message to be displayed.
     */
    public static String displayEventMessage() {
        return DISPLAY_LINE + System.lineSeparator() + "Please input a description, '/at', date\n" +
                "For eg. 'event meeting /at Mon 2-4pm'\n" + DISPLAY_LINE;
    }

    /**
     * Informs the user that the command is an invalid command.
     *
     * @return The message to be displayed.
     */
    public static String displayInvalidInputMessage() {
        return ("Please input a valid command:)\n" + DISPLAY_LINE);
    }

    /**
     * Informs the user that the saved file cannot be loaded.
     *
     * @return The message to be displayed.
     */
    public static String displayInvalidLoadmessage() {
        return("There was an error in loading the file" + DISPLAY_LINE);
    }

    /**
     * Displays a greeting message when the program is initialized.
     */
    public void displayGreetMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(ANSI_BLUE + DISPLAY_LINE + System.lineSeparator() + logo);
        System.out.println("Hello! Duke here!:)");
        System.out.print("Is there anything I can do for you?\n" + DISPLAY_LINE + System.lineSeparator()
                + ANSI_RESET);
    }

    /**
     * Displays the items in the task list.
     *
     * @param taskList The TaskList object that contains the tasks.
     * @throws DukeException Exception that is thrown when list is empty.
     */
    public static void listItems (TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException(Ui.displayListMessage());
        } else {
            System.out.println(DISPLAY_LINE  + System.lineSeparator() + "Here are the tasks in your list: ");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(taskList.get(i));
            }
            System.out.println(DISPLAY_LINE);
        }
    }

    /**
     * Saves the current list and bids the user goodbye.
     *
     * @param storage Storage object that contains the saveFile method.
     * @param taskList The TaskList object that contains the tasks.
     */
    public static void exit(Storage storage, TaskList taskList) {
        try {
            storage.saveFile(taskList);
            System.out.println("Bye. Hope to see you again soon!");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public String readCommand() {
        return in.nextLine();
    }

}
