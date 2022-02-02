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

        String endOfSection = "___________________________________________________";

        System.out.println(logo + "\nHello! I'm Duke\nWhat can I do for you?");
        System.out.println(endOfSection);

        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println(endOfSection);
            String[] arrOfStr = line.split(" ", 2);
            if (arrOfStr[0].equals("list")) {
                if (allTasks.isEmpty()) {
                    System.out.println(endOfSection);
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < allTasks.size(); ++i) {
                        System.out.println(String.format("%d.%s", i + 1, allTasks.get(i)));
                    }
                    System.out.println(endOfSection);
                }
            } else if (arrOfStr[0].equals("todo")) {
                // toDo method
                allTasks.add(new ToDo(arrOfStr[1]));
                System.out.println(String.format("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.",
                        allTasks.get(allTasks.size() - 1), allTasks.size()));
                System.out.println(endOfSection);
            } else if (arrOfStr[0].equals("deadline")) {
                // Deadline method
                String[] deadlineDescription = arrOfStr[1].split("/by");
                allTasks.add(new Deadline(deadlineDescription[0], deadlineDescription[1]));
                System.out.println(String.format("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.",
                        allTasks.get(allTasks.size() - 1), allTasks.size()));
                System.out.println(endOfSection);
            } else if (arrOfStr[0].equals("event")) {
                // Event method
                String[] eventDescription = arrOfStr[1].split("/at");
                allTasks.add(new Event(eventDescription[0], eventDescription[1]));
                System.out.println(String.format("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.",
                        allTasks.get(allTasks.size() - 1), allTasks.size()));
                System.out.println(endOfSection);
            } else if (arrOfStr[0].equals("mark")) {
                allTasks.get(Integer.parseInt(arrOfStr[1]) - 1).markAsDone();
                System.out.println(String.format("Nice! I've marked this task as done:%n%s",
                        allTasks.get(Integer.parseInt(arrOfStr[1]) - 1)));
                System.out.println(endOfSection);
            } else if (arrOfStr[0].equals("unmark")) {
                allTasks.get(Integer.parseInt(arrOfStr[1]) - 1).unmark();
                System.out.println(String.format("Ok, I've marked this task as not done yet:%n%s",
                        allTasks.get(Integer.parseInt(arrOfStr[1]) - 1)));
                System.out.println(endOfSection);
            } else {
                System.out.println(endOfSection);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(endOfSection);
    }
}
