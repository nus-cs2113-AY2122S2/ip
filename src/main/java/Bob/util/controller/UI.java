package bob.util.controller;

/**
 * A helper that handles the printing of messages.
 */
public class UI {
    // Miscellaneous Text
    public static final String BORDER = "____________________________________________________________";
    public static final String LOGO = ".______     ______   .______\n"
            + "\t|   _  \\   /  __  \\  |   _  \\\n"
            + "\t|  |_)  | |  |  |  | |  |_)  |\n"
            + "\t|   _  <  |  |  |  | |   _  <\n"
            + "\t|  |_)  | |  `--'  | |  |_)  |\n"
            + "\t|______/   \\______/  |______/\n";
    // Messages
    public static final String INTRO_GREETING = "Hello! I'm Bob ʘ‿ʘ";
    public static final String INTRO_QUESTION = "What can I do for you today?";
    public static final String OUTRO_GOODBYE = "See you next time. /|\\(◦.◦)/|\\";
    public static final String MESSAGE_TASK_LIST = "Task list:";
    public static final String MESSAGE_NO_TASKS = "There are currently no tasks. ¯\\_(ツ)_/¯";
    public static final String MESSAGE_TASK_MARKED = "The following task has been checked off:";
    public static final String MESSAGE_TASK_UNMARKED = "The following task has yet to be completed:";
    public static final String MESSAGE_TASK_COUNT = "The number of tasks amounts to: ";
    public static final String MESSAGE_DELETE_SUCCESS = "The following task has been deleted:";
    public static final String MESSAGE_INVALID_COMMAND = "Sorry, I do not understand.";
    public static final String MESSAGE_INVALID_ARGC = "Invalid number of arguments. ( °□°)";
    public static final String MESSAGE_INVALID_ID_NUMBER = "Invalid task id. ┬┴┬┴┤ω・)";
    public static final String MESSAGE_INVALID_ID_FORMAT = "The task id needs to be a number! (╯°□°）╯︵";
    public static final String MESSAGE_DEADLINE_USAGE = "Usage: deadline <task> /by <deadline>";
    public static final String MESSAGE_EVENT_USAGE = "Usage: event <task> /at <date,time>";
    public static final String MESSAGE_TASK_LIMIT_REACHED = "No more tasks can be created :(";
    public static final String MESSAGE_FOUND_MATCHING_TASK = "The following tasks matches your search:";
    public static final String MESSAGE_FOUND_NO_MATCH = "No tasks matches your description OTZ";

    /**
     * Prints the message prepended by a tab.
     *
     * @param message Text to be printed.
     */
    public static void printTab(String message) {
        System.out.print("\t" + message);
    }

    /**
     * Prints the message prepended by a tab and appended by a new line.
     *
     * @param message Text to be printed.
     */
    public static void printlnTab(String message) {
        System.out.println("\t" + message);
    }

    /**
     * Prints a horizontal border for actions performed.
     */
    public static void printBorder() {
        printlnTab(BORDER);
    }

    /**
     * Prints the stated error message at the end of command execution.
     *
     * @param message an Error message.
     */
    public static void printError(String message) {
        printlnTab(message);
        printBorder();
    }

    /**
     * Prints the initial greetings when the program starts.
     */
    public static void greetings() {
        printBorder();
        printlnTab(LOGO);
        printlnTab(INTRO_GREETING);
        printlnTab(INTRO_QUESTION);
        printBorder();
    }

    /**
     * Prints the exit message.
     */
    public static void goodBye() {
        printBorder();
        printlnTab(OUTRO_GOODBYE);
        printBorder();
    }
}
