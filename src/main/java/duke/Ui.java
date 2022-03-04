package duke;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;

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

    public void showError(String e) {
        System.out.println(e);
    }

    public void showLoadingError() {
        System.out.println("Error loading data!");
        System.out.println("New database created.");
    }

    public String readCommand() {
        String fullCommand = this.in.nextLine();
        return fullCommand;
    }
}
