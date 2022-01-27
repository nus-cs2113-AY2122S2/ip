// import libraries here

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> descriptions = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        echo();
    }

    private static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        // get user input
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                showList();
                line = in.nextLine();
                continue;
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("added: " + line);
            descriptions.add(line);
            System.out.println("    ____________________________________________________________");
            line = in.nextLine();
        }
        if (line.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Bye. Hope to see you again soon!");
            System.out.println("    ____________________________________________________________");
        }
    }

    private static void showList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < descriptions.size(); i++) {
            System.out.println(i+1 + "." + descriptions.get(i));
        }
    }

    private static void greeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }
}