package duke;

import java.io.File;
import java.util.Scanner;

/**
 * Start the application and the interaction with the user.
 */
public class Duke {
    public static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String END_OF_SECTION = "___________________________________________________";

    public static void main(String[] args) {
        printGreeting();
        File duke = new File("./Duke.txt");
        new FileManager(duke);
        new TaskManager(duke);

        printExitProgram();
    }

    /**
     * Greet the user when the application is started.
     */
    private static void printGreeting() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(END_OF_SECTION);
    }

    /**
     * Print the Goodbye message and exits the program.
     */
    private static void printExitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(END_OF_SECTION);
    }
}
