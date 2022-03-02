package alexis.ui;

import alexis.taskList.TaskList;

import java.io.InputStream;
import java.util.Scanner;

import static alexis.task.Task.BUFFER;

/**
 * Stores print messages that are too bulky to fit in their own classes.
 */
public class Ui {

    public static final String BORDER_LINE = "---------------------------------------------------------------";

    public static final String HELLO = "Hello! I'm Alexis, your trusty helper!";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ALEXIS_ICON =
            ANSI_PURPLE +
            "      __      ___       _______  ___  ___   __      ________     \n" +
            "     /\"\"\\    |\"  |     /\"     \"||\"  \\/\"  | |\" \\    /\"       )    \n" +
            "    /    \\   ||  |    (: ______) \\   \\  /  ||  |  (:   \\___/     \n" +
            "   /' /\\  \\  |:  |     \\/    |    \\\\  \\/   |:  |   \\___  \\       \n" +
            "  //  __'  \\  \\  |___  // ___)_   /\\.  \\   |.  |    __/  \\\\      \n" +
            " /   /  \\\\  \\( \\_|:  \\(:      \"| /  \\   \\  /\\  |\\  /\" \\   :)     \n" +
            "(___/    \\___)\\_______)\\_______)|___/\\___|(__\\_|_)(_______/      \n" +
            "                                                                 " + ANSI_RESET;
    public static final String PROMPT =
            "What can I do for you? ^-^\n\n" +
            "Hint: You may use these commands to navigate around:\n" +
            "[list] [todo] [deadline] [event] [mark] [unmark] [delete] [bye]\n" +
            "[show] [find]";

    public static final String ADD_NEW_TASK_MESSAGE = "Got it! I've added this task:";
    public static final String MARK_AS_DONE_MESSAGE = "Great! I've marked this task as done:";
    public static final String MARK_AS_UNDONE_MESSAGE = "Ok. I've marked this task as not done yet:";

    public static final String DEADLINE_EXCEPTION_MESSAGE_TEXT = "Oops!! The description of a deadline "
            + "has to have the format:\n    (task description) /by (deadline time)";
    public static final String EVENT_EXCEPTION_MESSAGE_TEXT = "Oops!! The description of an event "
            + "has to have the format:\n    (task description) /at (event time)";

    public static final String LOADING_ERROR_MESSAGE = "./data/tasks.txt file not found..\nnew file created";

    private final Scanner in;

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Shows message(s) to the user
     */
    public static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    /**
     * Shows welcome message
     */
    public static void showWelcome() {
        showToUser(BORDER_LINE, HELLO, ALEXIS_ICON, PROMPT, BORDER_LINE);
    }

    /**
     * Prints out the task that has been deleted.
     *
     * @param tasks Alexis.tasks
     * @param numOfTasks Alexis.tasks.getListSize()
     * @param taskNumber Task number of deleted task
     */
    public static void printDeleteOutput(TaskList tasks, int numOfTasks, int taskNumber) {
        showToUser(
                "Noted. I've removed this task:",
                BUFFER + tasks.getTask(taskNumber).toString(),
                "Now, you have " + (numOfTasks - 1) + " tasks in the list.");
    }

    /**
     * Advances the scanner to the next line
     *
     * @return Scanner for next line
     */
    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        showToUser(BORDER_LINE);
    }

    public void showLoadingError() {
        showToUser(LOADING_ERROR_MESSAGE);
    }
}
