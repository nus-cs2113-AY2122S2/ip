import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
            if (line.equals("list")) {
                ToDoList.displayAllTasks();
                System.out.println(endOfSection);
            } else {
                ToDoList tasks = new ToDoList(line);
                System.out.println("Added: " + String.format("%s", line));
                System.out.println(endOfSection);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(endOfSection);
    }
}
