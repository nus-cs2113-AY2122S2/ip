public class Ui {
    public static final String ANSI_REST = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    private static String drawBorder(String text) {
        return "____________________________________________________________\n"
                + text + "\n"
                + "____________________________________________________________\n";
    }

    public static void print(String text) {
        System.out.println(drawBorder((text)));
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

    public static String markTaskMsg(Task task, boolean markDone) {
        if (markDone) {
            return String.format("Nice! I've marked this task as done:\n%s", task);
        }
        return String.format("OK, I've marked this task as not done yet:\n%s", task);
    }

    // Exception Messages
    private static String errorFormatting(String message) {
        return ANSI_RED + message + ANSI_REST;
    }

    public static String missingDescription(String command) {
        return errorFormatting(String.format("The description of %s cannot be empty!", command));
    }

    public static String inputInWrongFormat() {
        return errorFormatting(String.format("The input is in the wrong format!"));
    }

    public static String missingDate() {
        return errorFormatting(String.format("The date/time is missing!"));
    }

    public static String taskIdOutOfBound(int taskId) {
        return errorFormatting(String.format("Task %d does not exist!", taskId));
    }

    public static String taskIdInWrongFormat() {
        return errorFormatting(String.format("The task ID has to be a number!"));
    }

    public static  String invalidInput() {
        return errorFormatting(String.format("Invalid input!"));
    }
}
