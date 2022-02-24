package duke;

import java.io.File;
import java.util.Scanner;

public class Duke {
    public static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String END_OF_SECTION = "___________________________________________________";

    public static void main(String[] args) {
//        try {
//            File file = new File("./Duke/duke.txt");
//        } catch (IOException e) {
//            System.out.println("An error occured.");
//        }

        File duke = FileManager.openFile();

        printGreeting();
        new TaskManager(duke);
        printExitProgram();
    }

    private static void printGreeting() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(END_OF_SECTION);
    }

    private static void printExitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(END_OF_SECTION);
    }
}
