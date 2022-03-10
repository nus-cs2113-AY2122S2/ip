package duke;

/**
 * Represents a UI that handles message printing.
 */
public class Ui {
    public static final String ANSI_REST = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    private static String drawBorder(String text) {
        return "____________________________________________________________\n"
                + text + "\n"
                + "____________________________________________________________\n";
    }

    /**
     * Prints output message with borders.
     * @param text Output message.
     */
    public static void printText(String text) {
        System.out.println(drawBorder((text)));
    }

    /**
     * Prints error message with borders.
     * @param e Exception.
     */
    public static void printError(Exception e) {
        System.out.println(drawBorder(formatError(e.getMessage())));
    }

    /**
     * Returns welcome message.
     */
    public static String welcomeMessage() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * Returns exit message.
     */
    public static String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns output message of the added task.
     *
     * @param task Added task.
     * @param size Size of TaskList.
     */
    public static String addTaskMsg(Task task, int size) {
        return String.format("Got it. I've added this task:\n%s\n" +
                        "Now you have %d tasks in list.",
                        task, size);
    }

    /**
     * Returns output message of the deleted task.
     *
     * @param task Deleted task.
     * @param size Size of TaskList.
     */
    public static String delTaskMsg(Task task, int size) {
        return String.format("Got it. I've removed this task:\n%s\n" +
                        "Now you have %d tasks in list.",
                        task, size);
    }

    /**
     * Returns output message of the duplicate task.
     *
     * @param task Duplicate task.
     * @param size Size of TaskList.
     */
    public static String dupTaskMsg(Task task, int size) {
        return String.format("This task already exists:\n%s\n" +
                        "Now you have %d tasks in list.",
                        task, size);
    }

    /**
     * Returns output message of the marked task.
     *
     * @param task Marked task.
     * @param markDone Parameter representing whether the task is done.
     */
    public static String markTaskMsg(Task task, boolean markDone) {
        if (markDone) {
            return String.format("Nice! I've marked this task as done:\n%s", task);
        }
        return String.format("OK, I've marked this task as not done yet:\n%s", task);
    }

    /**
     * Returns output message of the TaskList.
     * @param taskList List of all tasks.
     */
    public static String listTaskMsg(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "There is no task in your list.";
        }
        return "Here are the tasks in your list:\n" + taskList;
    }

    /**
     * Returns output message of the list of matching tasks.
     * @param taskList List of matching tasks.
     */
    public static String listMatchedTaskMsg(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "There is no matching task in your list.";
        }
        return "Here are the matching tasks in your list:\n" + taskList;
    }

    /**
     * Returns message in red colour, particularly for error messages.
     * @param text Message.
     */
    private static String formatError(String text) {
        return ANSI_RED + text + ANSI_REST;
    }

    /**
     * Returns error message of empty input error.
     */
    public static  String emptyInputError() {
        return "Input cannot be empty!";
    }

    /**
     * Returns error message of invalid command error.
     */
    public static  String invalidCommandError() {
        return "Invalid command!";
    }

    /**
     * Returns error message of missing command description error.
     * @param command User command.
     */
    public static String missingDescriptionError(String command) {
        return String.format("The description of %s cannot be empty!", command);
    }

    /**
     * Returns error message of missing date/time error.
     */
    public static String missingDateError() {
        return "The date/time is missing!";
    }

    /**
     * Returns error message of wrong input format error.
     */
    public static String wrongInputFormatError() {
        return "Wrong input format!";
    }

    /**
     * Returns error message showing the task with the task ID does not exist.
     * @param taskId Task ID.
     */
    public static String taskIdOutOfBoundError(int taskId) {
        return String.format("Task %d does not exist!", taskId);
    }

    /**
     * Returns error message showing the task ID is not a number.
     */
    public static String wrongTaskIdFormatError() {
        return "The task ID has to be a number!";
    }

    /**
     * Returns error message showing the data in the local file is in the wrong format.
     */
    public static String wrongFileFormatError(String line) {
        return String.format("Invalid input from local file: \t%s", line);
    }

    /**
     * Returns error message of reading file error.
     * @param e Exception.
     */
    public static String readingFileError(Exception e) {
        return String.format("Error reading file! \n%s", e.getMessage());
    }

    /**
     * Returns error message of writing file error.
     * @param e Exception.
     */
    public static String writingFileError(Exception e) {
        return String.format("Error writing to file! \n%s", e.getMessage());
    }
}
