package duke;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    public static final String DIVIDER = "____________________________________________________________";
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    public void showWelcome() {
        showToUser(
                DIVIDER,
                MESSAGE_WELCOME,
                DIVIDER);
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
