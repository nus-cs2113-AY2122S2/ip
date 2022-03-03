package duke;

import exceptions.DukeException;

import java.util.Scanner;

/**
 * Represents the user interface of Duke and its methods.
 */
public class Ui {

    /**
     * Prints the logo of Duke Task Manager.
     */
    public void printLogo() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Prints a line break on the command line.
     */
    public void printLine() {
        final String MESSAGE_BORDER = "    ____________________________________";
        System.out.println(MESSAGE_BORDER);
    }

    /** Greets the user at the start and the end of a Duke application run.
     * @param greeting String that denotes the type of greeting.
     */
    public void greetUser(String greeting) {
        if (greeting.equals("hi")) {
            printLogo();
            printLine();
            System.out.println("    Hello! I'm Duke");
            System.out.println("    What can I do for you?\n");
        } else {
            printLine();
            System.out.println("    Bye. Hope to see you again soon!");
            printLine();
        }
    }
}
