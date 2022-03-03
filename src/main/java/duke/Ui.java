package duke;

import java.util.Scanner;

public class Ui {
    public static String boundary = "____________________________________________________________" + System.lineSeparator();
    private static final Parser parser = new Parser();

    // Prints welcome message.
    public void sayHello() {
        String logo = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(boundary + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?" + System.lineSeparator() + boundary);
    }

    // Prints goodbye message.
    public void sayGoodbye() {
        System.out.print(boundary + "Bye. Hope to see you again soon!" + System.lineSeparator() + boundary);
    }

    // Interacts with user and edit the given task list accordingly.
    public void interact(TaskList tasks) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {
            if (line.equalsIgnoreCase("list")) {
                tasks.printList();
            } else if (line.toLowerCase().startsWith("mark")) {
                tasks.markTask(Integer.parseInt(line.substring(5)) - 1);
            } else if (line.toLowerCase().startsWith("unmark")) {
                tasks.unmarkTask(Integer.parseInt(line.substring(7)) - 1);
            } else if (line.toLowerCase().startsWith("delete")) {
                parser.tryDeleteTask(tasks, line);
            } else if (line.toLowerCase().startsWith("find")) {
                parser.tryFindTask(tasks, line);
            } else {
                parser.tryAddTask(tasks, line);
            }
            in = new Scanner(System.in);
            line = in.nextLine();
        }
    }
}
