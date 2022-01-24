import java.util.Scanner;

public class Duke {
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String RESET = "\033[0m";

    public static void listCommand(Task[] tasks) {
        if (Task.getNumTask() == 0) {
            System.out.println("No tasks yet!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getNumTask(); i++) {
                if (tasks[i].getStatus()) {
                    System.out.print(i+1 + ".[X] ");
                } else {
                    System.out.print(i+1 + ".[ ] ");
                }
                System.out.println(tasks[i].getDescription());
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static void unmarkCommand(String command, Task[] tasks){
        int taskIndex = Integer.parseInt(command.substring(7));
        tasks[taskIndex-1].unmarkAsDone();
        System.out.println("OK, I've marked this task as not done: ");
        System.out.println("[ ] " + tasks[taskIndex-1].getDescription() + System.lineSeparator());
    }

    public static void markCommand(String command, Task[] tasks) {
        int taskIndex = Integer.parseInt(command.substring(5));
        tasks[taskIndex-1].markAsDone();
        System.out.println("Nice, I've marked this task as done: ");
        System.out.println("[X] " + tasks[taskIndex-1].getDescription() + System.lineSeparator());
    }

    public static void addTask(String command, Task[] tasks) {
        Task newTask = new Task(command);
        tasks[Task.getNumTask()-1] = newTask;
        System.out.println("Added: " + command + "\n");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(PURPLE_BOLD_BRIGHT + logo + RESET);

        String command;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?\n");
        command = in.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listCommand(tasks);
            } else if (command.contains("unmark")) {
                unmarkCommand(command, tasks);
            } else if (command.contains("mark")) {
                markCommand(command, tasks);
            } else {
                addTask(command, tasks);
            }
            command = in.nextLine();
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
