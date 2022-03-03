package duke.ui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import duke.messages.Messages;



public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getCommand() {
        String input = in.nextLine();
        return input.strip();
    }

    public void printIntro() {
        out.println(Messages.MESSAGE_WELCOME);
    }

    public void printBye() {
        out.println(Messages.MESSAGE_GOODBYE);
    }

    public void showLoadingError() {
        out.println(Messages.MESSAGE_LOADING_ERROR);
    }

    public void PrintStringIndexOutOfBoundsException() {
        out.println(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }


    public static void printFileContents(String filePath) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

}