package duke;

public class Ui {
    public static final String ANSI_REST = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    private static String drawBorder(String text) {
        return "____________________________________________________________\n"
                + text + "\n"
                + "____________________________________________________________\n";
    }

    public static void printText(String text) {
        System.out.println(drawBorder((text)));
    }

    public static void printError(Exception e) {
        System.out.println(drawBorder(formatError(e.getMessage())));
    }

    public static String welcomeMessage() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    public static String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    // Command Messages
    public static String addTaskMsg(Task task, int size) {
        return String.format("Got it. I've added this task:\n%s\n" +
                        "Now you have %d tasks in list.",
                        task, size);
    }

    public static String delTaskMsg(Task task, int size) {
        return String.format("Got it. I've removed this task:\n%s\n" +
                        "Now you have %d tasks in list.",
                        task, size);
    }

    public static String dupTaskMsg(Task task, int size) {
        return String.format("This task already exists:\n%s\n" +
                        "Now you have %d tasks in list.",
                        task, size);
    }

    public static String markTaskMsg(Task task, boolean markDone) {
        if (markDone) {
            return String.format("Nice! I've marked this task as done:\n%s", task);
        }
        return String.format("OK, I've marked this task as not done yet:\n%s", task);
    }

    public static String listTaskMsg(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "There is no task in your list.";
        }
        return "Here are the tasks in your list:\n" + taskList.toString();
    }

    public static String listMatchedTaskMsg(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "There is no matching task in your list.";
        }
        return "Here are the matching tasks in your list:\n" + taskList;
    }

    // Exception Messages
    private static String formatError(String message) {
        return ANSI_RED + message + ANSI_REST;
    }

    public static  String emptyInputError() {
        return String.format("Input cannot be empty!");
    }

    public static  String invalidCommandError() {
        return String.format("Invalid command!");
    }

    public static String missingDescriptionError(String command) {
        return String.format("The description of %s cannot be empty!", command);
    }

    public static String missingDateError() {
        return String.format("The date/time is missing!");
    }

    public static String wrongInputFormatError() {
        return String.format("Wrong input format!");
    }

    public static String taskIdOutOfBoundError(int taskId) {
        return String.format("Task %d does not exist!", taskId);
    }

    public static String wrongTaskIdFormatError() {
        return String.format("The task ID has to be a number!");
    }

    public static String wrongFileFormatError(String line) {
        return String.format("Invalid input from local file: \t%s", line);
    }

    public static String readingFileError(Exception e) {
        return String.format("Error reading file! \n%s", e.getMessage());
    }

    public static String writingFileError(Exception e) {
        return String.format("Error writing to file! \n%s", e.getMessage());
    }
}
