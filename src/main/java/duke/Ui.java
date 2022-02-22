package duke;

import exceptions.DukeException;

import java.util.Scanner;

public class Ui {


    public void printLogo() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }
    public void printLine() {
        System.out.println("    ____________________________________");
    }
    public void greetUser(String greeting) {
        if (greeting.equals("hi")) {
            printLogo();
            printLine();
            System.out.println("    Hello! I'm Duke");
            System.out.println("    What can I do for you?\n");
        }
        else {
            printLine();
            System.out.println("    Bye. Hope to see you again soon!");
            printLine();
        }
    }
}
