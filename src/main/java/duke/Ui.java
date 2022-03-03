package duke;

/**
 * A user interface class that deals with interaction with the user.
 */
public class Ui {
    public static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String END_OF_SECTION = "___________________________________________________";

    /**
     * Greet the user when the application is started.
     */
    public static void printWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(END_OF_SECTION);
    }

    /**
     * Print the Goodbye message and exits the program.
     */
    public static void printExitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(END_OF_SECTION);
    }

    /**
     * Print a long line to represent the end of a section.
     */
    public static void setEndOfSection() {
        System.out.println(END_OF_SECTION);
    }

    public static void printInvalidCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means");
        Ui.setEndOfSection();
    }
}
