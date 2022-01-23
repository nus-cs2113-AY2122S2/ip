import java.util.Scanner;

public class Duke {
    private static final String line = "_______________"
            + "_______________"
            + "_______________"
            + "_______________";

    public static void list(Task[] tasks) {
        int printIndex = 1;
        for (int i = 0; i < Task.getCurrentIndex(); i++) {
            System.out.println(printIndex + ". ["
                    + tasks[i].getStatusIcon()
                    + "] " + tasks[i].getDescription());
            printIndex++;
        }
    }

    public static void mark(String[] words, Task[] tasks) {
        if (words.length < 2 ||
                Integer.parseInt(words[1]) > Task.getCurrentIndex()) {

            System.out.println("Bzzt! \n Please"
                    + " key in a valid task number "
                    + "to mark your task.");
            return;
        }

        int i = Integer.parseInt(words[1]) - 1;
        if (tasks[i].isDone()) {
            System.out.println("This task has already been marked!");
            return;
        }
        tasks[i].markAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + "  [" + tasks[i].getStatusIcon()
                + "] " + tasks[i].getDescription());
    }

    public static void unmark(String[] words, Task[] tasks) {
        if (words.length < 2 ||
            Integer.parseInt(words[1]) > Task.getCurrentIndex()) {

            System.out.println("Bzzt! \n Please"
                    + " key in a valid task number "
                    + "to unmark your task.");
            return;
        }

        int i = Integer.parseInt(words[1]) - 1;
        if (!tasks[i].isDone()) {
            System.out.println("This task was already unmarked!");
            return;
        }
        tasks[i].markAsUndone();
        System.out.println("Ok, I've marked this task as" +
                " not done yet:\n  ["
                + tasks[i].getStatusIcon()
                + "] " + tasks[i].getDescription());
    }

    public static void taskManager(String input, Task[] tasks) {
        System.out.println(line);
        String[] words = input.split(" ");
        switch(words[0]) {
        case "list":
            list(tasks);
            break;
        case "mark":
            mark(words, tasks);
            break;
        case "unmark":
            unmark(words, tasks);
            break;
        default:
            tasks[Task.getCurrentIndex()] = new Task(input);
            Task.increaseIndex();
            System.out.println("added: " + input);
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        System.out.println(line);
        System.out.print("Hello! I'm Bob,\n"
                + "your friendly virtual task manager.\n"
                + "How can I help you today?\n");
        System.out.println(line);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            taskManager(input, tasks);
            input = in.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon! :)");
        System.out.print(line);
    }
}
