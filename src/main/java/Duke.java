// import libraries here

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> descriptions = new ArrayList<>();
    private static ArrayList<Boolean> done = new ArrayList<>();

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
            if (line.startsWith("mark")) {
                int number = Integer.parseInt(line.substring(line.length() - 1));
                markAsDone(number);
                line = in.nextLine();
                continue;
            }
            if (line.startsWith("unmark")) {
                int number = Integer.parseInt(line.substring(line.length() - 1));
                unmarkTask(number);
                line = in.nextLine();
                continue;
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("added: " + line);
            descriptions.add(line);
            done.add(false);
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
            if (done.get(i)) {
                System.out.println("[X] " + descriptions.get(i));
            } else {
                System.out.println("[ ] " + descriptions.get(i));
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void unmarkTask(int number) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've marked this task as not done yet:");
        int index = number - 1;
        String description = descriptions.get(index);
        done.set(index, false);
        System.out.println("[ ] " + description);
        System.out.println("    ____________________________________________________________");
    }

    private static void markAsDone(int number) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        int index = number - 1;
        String description = descriptions.get(index);
        done.set(index, true);
        System.out.println("[X] " + description);
        System.out.println("    ____________________________________________________________");
    }

    private static void greeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }
}