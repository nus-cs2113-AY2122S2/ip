package alexis.ui;

import alexis.main.Alexis;
import alexis.task.Task;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static alexis.task.Task.BUFFER;

public class Ui {

    public static final String BORDER_LINE = "---------------------------------------------------------------";

    public static final String HELLO = "Hello! I'm Alexis, your trusty helper!";
    public static final String ALEXIS_ICON =
            "      __      ___       _______  ___  ___   __      ________     \n" +
                    "     /\"\"\\    |\"  |     /\"     \"||\"  \\/\"  | |\" \\    /\"       )    \n" +
                    "    /    \\   ||  |    (: ______) \\   \\  /  ||  |  (:   \\___/     \n" +
                    "   /' /\\  \\  |:  |     \\/    |    \\\\  \\/   |:  |   \\___  \\       \n" +
                    "  //  __'  \\  \\  |___  // ___)_   /\\.  \\   |.  |    __/  \\\\      \n" +
                    " /   /  \\\\  \\( \\_|:  \\(:      \"| /  \\   \\  /\\  |\\  /\" \\   :)     \n" +
                    "(___/    \\___)\\_______)\\_______)|___/\\___|(__\\_|_)(_______/      \n" +
                    "                                                                 ";
    public static final String PROMPT =
            "What can I do for you? ^-^\n\n" +
                    "Hint: You may use these commands to navigate around:\n" +
                    "[list] [todo] [deadline] [event] [mark] [unmark] [delete] [bye]\n" +
                    "[show]";

    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static final String SAD_FACE = ";-;";

    public static final String DISPLAY_TASK_MESSAGE = "Here are the tasks in your list:";
    public static final String EMPTY_LIST_MESSAGE = "Your list is empty. You have no tasks now.";
    public static final String INVALID_INPUT_MESSAGE = SAD_FACE
            + " Oops!! I'm sorry, but I don't know what that means :-(";
    public static final String ADD_NEW_TASK_MESSAGE = "Got it! I've added this task:";
    public static final String MARK_AS_DONE_MESSAGE = "Great! I've marked this task as done:";
    public static final String MARK_AS_UNDONE_MESSAGE = "Ok. I've marked this task as not done yet:";

    public static final String DEADLINE_EXCEPTION_MESSAGE_TEXT = " Oops!! The description of a deadline "
            + "has to have the format:\n    (task description) /by (deadline time)";
    public static final String EVENT_EXCEPTION_MESSAGE_TEXT = " Oops!! The description of an event "
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

    public static void showWelcome() {
        showToUser(BORDER_LINE, HELLO, ALEXIS_ICON, PROMPT, BORDER_LINE);
    }

    public static void showGoodbye() {
        showToUser(GOODBYE_MESSAGE);
    }

    public static void printDeleteOutput(ArrayList<Task> tasks, int numOfTasks, int taskNumber) {
        showToUser(
                "Noted. I've removed this task:",
                BUFFER + Alexis.tasks.getTask(taskNumber).toString(),
                "Now, you have " + (numOfTasks - 1) + " tasks in the list.");
    }

    public static void invalidInputMessage() {
        showToUser(INVALID_INPUT_MESSAGE);
    }

    public static void exceptionMessage(String exceptionText) {
        showToUser(SAD_FACE + exceptionText);
    }

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
