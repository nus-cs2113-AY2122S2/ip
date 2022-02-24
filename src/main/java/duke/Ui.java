package duke;

import java.util.Scanner;

/**
 * Handles the user interface.
 */
public class Ui {

    private final Scanner uiScan;

    public Ui() {
        this.uiScan = new Scanner(System.in);
    }

    /**
     * Prints 37 underscores to stdout to serve as a divider.
     */
    public static void printDivider() {
        System.out.println("_____________________________________");
    }

    /**
     * Prints a greeting with divider lines.
     */
    public void printGreeting() {
        printDivider();
        System.out.println("Hello! I'm Michel.");
        System.out.println("What can I do for you?");
        printDivider();
    }

    public String readCommand() {
        return uiScan.nextLine();
    }
}
