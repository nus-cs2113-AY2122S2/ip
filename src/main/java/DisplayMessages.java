public class DisplayMessages {
    final static String HORIZONTAL_LINE = "____________________________________________________________\n";

    public static void horizontalLine() {
        System.out.print(HORIZONTAL_LINE);
    }

    public static void outOfBounds() {
        System.out.println(HORIZONTAL_LINE
                + "No such task exists. Add more tasks first!\n"
                + HORIZONTAL_LINE);
    }

    public static void printTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        task.printItem();
    }

    public static void printTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        task.printItem();
    }


    public static void startingMessage() {
        String message = HORIZONTAL_LINE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE;

        System.out.println(message);
    }

    public static void closingMessage() {
        String message = HORIZONTAL_LINE + " Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE;
        System.out.println(message);
    }

    public static void emptyList() {
        String message = HORIZONTAL_LINE
                    + "Your list is currently empty. Add some items to your list first.\n"
                    + HORIZONTAL_LINE;
        System.out.println(message);
    }

    public static void taskAdded(String taskDescription) {
        String message = HORIZONTAL_LINE
                + "added: " + taskDescription + "\n"
                + HORIZONTAL_LINE;
        System.out.println(message);
    }

    public static void markError() {
        System.out.println("This task is already marked as not done! Did you mean to mark it?");
    }

    public static void unmarkError() {
        System.out.println("This task is already marked as done! Did to mean to unmark it?");
    }

    public static void invalidInput() {
        System.out.println(HORIZONTAL_LINE
                + "You have entered an invalid command. Please try again\n"
                + HORIZONTAL_LINE);
    }
}
