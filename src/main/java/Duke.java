import java.util.Scanner;

public class Duke {
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String RESET_COLOUR = "\033[0m";
    public static final int MARK_COMMAND_INDEX = 5;
    public static final int UNMARK_COMMAND_INDEX = 7;
    public static final int CONVERT_I_TO_TASK = 1;
    public static final int CONVERT_TASK_TO_I = -1;
    public static final int MAX_TASKS = 100;

    public static void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(PURPLE_BOLD_BRIGHT + logo + RESET_COLOUR);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?\n");
    }

    public static String getCommand() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();

        return command;
    }

    public static void printTasks(Task[] tasks) {
        for (int i = 0; i < Task.getNumTask(); i++) {
            if (tasks[i].getStatus()) {
                System.out.print(i+CONVERT_I_TO_TASK + ".[X] ");
            } else {
                System.out.print(i+CONVERT_I_TO_TASK + ".[ ] ");
            }
            System.out.println(tasks[i].getDescription());
        }
    }

    public static void listCommand(Task[] tasks) {
        if (Task.getNumTask() == 0) {
            System.out.println("No tasks yet!");
        } else {
            System.out.println("Here are the tasks in your list:");
            printTasks(tasks);
        }
        System.out.print(System.lineSeparator());
    }

    public static void unmarkCommand(String command, Task[] tasks){
        int taskIndex = Integer.parseInt(command.substring(UNMARK_COMMAND_INDEX));
        tasks[taskIndex+CONVERT_TASK_TO_I].unmarkAsDone();
        System.out.println("OK, I've marked this task as not done: ");
        System.out.println("[ ] " + tasks[taskIndex+CONVERT_TASK_TO_I].getDescription() + System.lineSeparator());
    }

    public static void markCommand(String command, Task[] tasks) {
        int taskIndex = Integer.parseInt(command.substring(MARK_COMMAND_INDEX));
        tasks[taskIndex+CONVERT_TASK_TO_I].markAsDone();
        System.out.println("Nice, I've marked this task as done: ");
        System.out.println("[X] " + tasks[taskIndex+CONVERT_TASK_TO_I].getDescription() + System.lineSeparator());
    }

    public static void addTask(String command, Task[] tasks) {
        Task newTask = new Task(command);
        tasks[Task.getNumTask()+CONVERT_TASK_TO_I] = newTask;
        System.out.println("Added: " + command + "\n");
    }

    public static void executeCommand(Task[] tasks) {
        String command = getCommand();

        if (command.equals("bye")) {
            System.out.println("Bye! Hope to see you again soon!");
            System.exit(0);
        } else if (command.equals("list")) {
            listCommand(tasks);
        } else if (command.contains("unmark")) {
            unmarkCommand(command, tasks);
        } else if (command.contains("mark")) {
            markCommand(command, tasks);
        } else {
            addTask(command, tasks);
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];

        printIntro();

        while (true) {
            executeCommand(tasks);
        }
    }
}
