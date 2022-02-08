public class Ui {
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

    public static String markTaskMsg(Task task, boolean markDone) {
        if (markDone) {
            return String.format("Nice! I've marked this task as done:\n%s", task);
        }
        return String.format("OK, I've marked this task as not done yet:\n%s", task);
    }

    // Exception Messages
    public static String emptyDescription(String command) {
        return String.format("The description of %s cannot be empty!", command);
    }

    public static String taskIdOutOfBound(int taskId) {
        return String.format("Task with ID %d does not exist!", taskId);
    }

    public static String taskIdInWrongFormat() {
        return String.format("The task ID has to be a number!");
    }
}
