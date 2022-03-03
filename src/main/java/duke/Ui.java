package duke;

import java.util.Scanner;

public class Ui {

    public static void greeting() {
        String greeting = "----------------------------------------------\n"
                + "Hello! I'm Duke.Duke\n"
                + "What can I do for you?\n"
                + "----------------------------------------------\n";
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(greeting);
    }

    public static void farewell() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
    }

    public static String getInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }
}
