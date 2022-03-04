package duke.ui;

/**
 * Methods for printing the text UI.
 */
public class CommandLineOutputUtil {
    private static final String MESSAGE_BORDER =
            "____________________________________________________________";

    /**
     * Prints a message following a defined format.
     *
     * @param message Message to print.
     */
    public static void printFormat(String message) {
        System.out.println(MESSAGE_BORDER);
        System.out.println(message);
        System.out.println(MESSAGE_BORDER);
    }

    /**
     * Prints start message.
     */
    static void greet() {
        printFormat(" Hey there! I'm Duke\n" +
                " What can I do for you? uwu");
    }

    /**
     * Prints exit message.
     */
    static void bye() {
        printFormat(" Aw, are you leaving now?\n" +
                " Hope to see you again soon!");
    }
}
