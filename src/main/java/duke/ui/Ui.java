package duke.ui;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui () {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints String wrapped inside two divider lines.
     *
     * @param stringWithinDivider String to be wrapped
     */
    public static void printWithDivider(String stringWithinDivider) {
        String breakLine = "\t____________________________________________________________";
        System.out.println(breakLine);
        stringWithinDivider = stringWithinDivider.replace("\n", "\n\t");
        System.out.println("\t" + stringWithinDivider);
        System.out.println(breakLine);
    }

    /**
     * Greets the user
     */
    public void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Ui.printWithDivider("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Reads input typed in by user
     *
     * @return input command as string
     */
    public String readCommand() {
        String line = sc.nextLine();
        return line;
    }

    /**
     * Prints the error message
     *
     * @param e error
     */
    public void showError(DukeException e) {
        System.out.println(e.toString());
    }
}
