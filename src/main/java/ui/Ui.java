package ui;

public class Ui {

    public void startProgram() {
        showWelcomeMessage();
        greet();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }

    public void greet() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
