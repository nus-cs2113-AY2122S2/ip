package Duke.Ui;

import java.util.Scanner;

/**
 * handles all the interface/input directyly from the user
 * 
 */
public class Ui {
    private final Scanner input;
    private static final String LINE_SEPARATOR = "-----------------------------------------";

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("    Hello I'm Duke.Duke\n    What can I help you with?");
        System.out.println(LINE_SEPARATOR);
    }

    public String readInput() {
        String in = input.nextLine();
        return in;
    }

    public void printGoodByeMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(
                "Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }
}
