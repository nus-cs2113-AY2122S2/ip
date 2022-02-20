package duke;

import duke.common.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class UI {
    private final Scanner in;
    private final PrintStream out;

    public UI() {
        this(System.in, System.out);
    }

    public UI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads the text entered by the user.
     *
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String input = in.nextLine();
        return input.strip();
    }

    /**
     *  Prints the intro message upon the start of the application.
     */
    public void printIntro() {
        out.println(Messages.INTRO_LOGO);
        printMessageWithBorder(Messages.WELCOME_MESSAGE);
    }

    /**
     *  Prints the outro message before the application ends.
     */
    public void printOutro() {
        out.println(Messages.OUTRO_LOGO);
        printMessageWithBorder(Messages.GOODBYE_MESSAGE);
    }

    /**
     *  Prints the message enclosed with border
     */
    public void printMessageWithBorder(String message){
        out.println(Messages.BORDER);
        out.println(message);
        out.println(Messages.BORDER);
    }

    /**
     *  Prints a border
     */
    public static void printBorder() {
        System.out.println(Messages.BORDER);
    }
}
