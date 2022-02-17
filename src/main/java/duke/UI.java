package duke;

import duke.common.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

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

    public String getUserCommand() {
        String input = in.nextLine();
        return input;
    }

    public void printIntro() {
        out.println(Messages.INTRO_LOGO);
        printMessageWithBorder(Messages.WELCOME_MESSAGE);
    }

    public void printOutro() {
        out.println(Messages.OUTRO_LOGO);
        printMessageWithBorder(Messages.GOODBYE_MESSAGE);
    }

    public void printMessageWithBorder(String message){
        out.println(Messages.BORDER);
        out.println(message);
        out.println(Messages.BORDER);
    }

    public static void printBorder() {
        System.out.println(Messages.BORDER);
    }
}
