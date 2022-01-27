import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> allTasks = new ArrayList<Task>();
        String line;
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String endOfSection = "___________________________________________________\n";

        System.out.println(logo + "\nHello! I'm Duke\nWhat can I do for you?");
        System.out.println(endOfSection);

        line = in.nextLine();

        while (!line.equals("bye")) {
            if (allTasks.isEmpty()) {
                if (!line.equals("list")) {
                    allTasks.add(new Task(line));
                    System.out.println("Added: " + String.format("%s", line));
                    System.out.println(endOfSection);
                }
            } else {
                if (line.equals("list")) {
                    for (int i = 0; i < allTasks.size(); ++i) {
                        Task currentTask = allTasks.get(i);
                        System.out.println(String.format("%d. [%s] %s", i + 1, currentTask.getStatusIcon(), currentTask.getTask()));
                    }
                    System.out.println(endOfSection);
                } else if (line.equals("mark")) {
                    int pos = in.nextInt();
                    Task currentTask = allTasks.get(pos - 1);
                    currentTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("[%s] %s", currentTask.getStatusIcon(), currentTask.getTask()));
                    System.out.println(endOfSection);
                    in.nextLine();
                } else if (line.equals("unmark")) {
                    int pos = in.nextInt();
                    Task currentTask = allTasks.get(pos - 1);
                    currentTask.unmark();
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(String.format("[%s] %s", currentTask.getStatusIcon(), currentTask.getTask()));
                    System.out.println(endOfSection);
                    in.nextLine();
                } else {
                    allTasks.add(new Task(line));
                    System.out.println("Added: " + String.format("%s", line));
                    System.out.println(endOfSection);
                }
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(endOfSection);
    }
}
