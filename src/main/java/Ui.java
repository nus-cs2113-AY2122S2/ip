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
        System.out.println(drawBorder(formatError(e.toString())));
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

    // Exception Messages
    private static String formatError(String message) {
        return ANSI_RED + message + ANSI_REST;
    }

    public static  String invalidCommand() {
        return String.format("Invalid command!");
    }

    public static String missingDescription(String command) {
        return String.format("The description of %s cannot be empty!", command);
    }

    public static String wrongInputFormat() {
        return String.format("Wrong input format!");
    }

    public static String missingDate() {
        return String.format("The date/time is missing!");
    }

    public static String taskIdOutOfBound(int taskId) {
        return String.format("Task %d does not exist!", taskId);
    }

    public static String wrongTaskIdFormat() {
        return String.format("The task ID has to be a number!");
    }

    public static String wrongFileFormat(String line) {
        return String.format("Invalid input from local file! \n%s", line);
    }
}
