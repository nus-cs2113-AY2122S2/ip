package duke;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private static final String DIVIDER = "____________________________________________________________";

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        this.showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        this.showLine();
    }

    // Shows message(s) to the user
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public String readCommand() {
        String fullCommand = this.in.nextLine();
        return fullCommand;
    }
}
