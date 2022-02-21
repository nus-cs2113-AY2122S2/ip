package duke;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ui {

    private Scanner uiScan;
    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.uiScan = new Scanner(System.in);
        this.taskList = taskList;
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
