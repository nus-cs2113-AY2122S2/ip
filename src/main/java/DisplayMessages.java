public class DisplayMessages {
    final static String HORIZONTAL_LINE = "____________________________________________________________\n";
    final static String SOFT_BREAK = "============================================================\n";

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
        String message =
                  " _    _      _                          \n"
                + "| |  | |    | |                         \n"
                + "| |  | | ___| | ___ ___  _ __ ___   ___ \n"
                + "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n"
                + "\\  /\\  /  __/ | (_| (_) | | | | | |  __/\n"
                + " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___\n"
                + " _            ___                  _     \n"
                + "| |          |_  |                (_)    \n"
                + "| |_ ___       | | __ _ _ ____   ___ ___ \n"
                + "| __/ _ \\      | |/ _` | '__\\ \\ / / / __|\n"
                + "| || (_) | /\\__/ / (_| | |   \\ V /| \\__ \\\n"
                + " \\__\\___/  \\____/ \\__,_|_|    \\_/ |_|___/\n"
                + "How can I help you today?\n"
                + HORIZONTAL_LINE;

        System.out.println(message);
    }

    public static void closingMessage() {
        String message = HORIZONTAL_LINE
                + "______            _ \n"
                + "| ___ \\          | |\n"
                + "| |_/ /_   _  ___| |\n"
                + "| ___ \\ | | |/ _ \\ |\n"
                + "| |_/ / |_| |  __/_|\n"
                + "\\____/ \\__, |\\___(_)\n"
                + "        __/ |       \n"
                + "       |___/        \n"
                + "______                      _                   _                           \n"
                + "| ___ \\                    (_)                 | |                          \n"
                + "| |_/ /____      _____ _ __ _ _ __   __ _    __| | _____      ___ __        \n"
                + "|  __/ _ \\ \\ /\\ / / _ \\ '__| | '_ \\ / _` |  / _` |/ _ \\ \\ /\\ / / '_ \\       \n"
                + "| | | (_) \\ V  V /  __/ |  | | | | | (_| | | (_| | (_) \\ V  V /| | | |_ _ _ \n"
                + "\\_|  \\___/ \\_/\\_/ \\___|_|  |_|_| |_|\\__, |  \\__,_|\\___/ \\_/\\_/ |_| |_(_|_|_)\n"
                + "                                     __/ |                                  \n"
                + "                                    |___/                                   \n"
                + HORIZONTAL_LINE;
        System.out.println(message);
    }

    public static void emptyList() {
        String message = HORIZONTAL_LINE
                    + "Your list is currently empty. Try adding some items to your list first.\n"
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
