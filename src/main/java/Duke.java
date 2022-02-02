import java.util.Scanner;

public class Duke {
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String RESET_COLOUR = "\033[0m";
    public static final String PROMPT = "> ";

    public static final int COMMAND_INDEX = 0;
    public static final int DESCRIPTION_INDEX = 1;

    public static final int DEADLINE_INDEX = 0;
    public static final int BY_INDEX = 1;

    public static final int EVENT_INDEX = 0;
    public static final int AT_INDEX = 1;

    public static final int CONVERT_I_TO_TASK = 1;
    public static final int CONVERT_TASK_TO_I = -1;

    public static final int SPLIT_LIMIT = 2;
    public static final int MAX_TASKS = 100;

    public static void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(PURPLE_BOLD_BRIGHT + logo + RESET_COLOUR);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?" + System.lineSeparator());
    }

    public static String[] parseInput(String input) {
        String[] inputAsArray = input.split(" ", SPLIT_LIMIT);
        return inputAsArray;
    }

    public static void printTasks(Task[] tasks) {
        for (int i = 0; i < Task.getNumTask(); i++) {
            System.out.print(i + CONVERT_I_TO_TASK + ".");
            tasks[i].printInfo();
        }
    }

    public static void printAddedPrompt(Task newtask) {
        System.out.print("Added: ");
        newtask.printInfo();

        if (Task.getNumTask() > 1) {
            System.out.println("You now have " + Task.getNumTask() + " tasks in the list" + System.lineSeparator());
        } else {
            System.out.println("You now have " + Task.getNumTask() + " task in the list" + System.lineSeparator());
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

    public static void unmarkCommand(String parsedInput, Task[] tasks){
        int taskIndex = Integer.parseInt(parsedInput);
        tasks[taskIndex + CONVERT_TASK_TO_I].unmarkAsDone();
        tasks[taskIndex + CONVERT_TASK_TO_I].printInfo();
        System.out.print(System.lineSeparator());
    }

    public static void markCommand(String parsedInput, Task[] tasks) {
        int taskIndex = Integer.parseInt(parsedInput);
        tasks[taskIndex + CONVERT_TASK_TO_I].markAsDone();
        tasks[taskIndex + CONVERT_TASK_TO_I].printInfo();
        System.out.print(System.lineSeparator());
    }

    public static void todoCommand(String parsedInput, Task[] tasks) {
        Todo newTodo = new Todo(parsedInput);
        tasks[Task.getNumTask() + CONVERT_TASK_TO_I] = newTodo;

        printAddedPrompt(newTodo);
    }

    public static void deadlineCommand(String parsedInput, Task[] tasks) {
        String[] parsedInputAsArray = parsedInput.split(" /by ", SPLIT_LIMIT);
        Deadline newDeadline = new Deadline(parsedInputAsArray[DEADLINE_INDEX], parsedInputAsArray[BY_INDEX]);
        tasks[Task.getNumTask() + CONVERT_TASK_TO_I] = newDeadline;

        printAddedPrompt(newDeadline);
    }

    public static void eventCommand(String parsedInput, Task[] tasks) {
        String[] parsedInputAsArray = parsedInput.split(" /at ", SPLIT_LIMIT);
        Event newEvent = new Event(parsedInputAsArray[EVENT_INDEX], parsedInputAsArray[AT_INDEX]);
        tasks[Task.getNumTask() + CONVERT_TASK_TO_I] = newEvent;

        printAddedPrompt(newEvent);
    }

    public static void executeCommand(String input, Task[] tasks) {
        String[] parsedInput = parseInput(input);

        switch(parsedInput[COMMAND_INDEX]) {
        case "bye":
            System.out.println("Bye, hope to see you again soon!");
            System.exit(0);
        case "list":
            listCommand(tasks);
            break;
        case "mark":
            markCommand(parsedInput[DESCRIPTION_INDEX], tasks);
            break;
        case "unmark":
            unmarkCommand(parsedInput[DESCRIPTION_INDEX], tasks);
            break;
        case "todo":
            todoCommand(parsedInput[DESCRIPTION_INDEX], tasks);
            break;
        case "deadline":
            deadlineCommand(parsedInput[DESCRIPTION_INDEX], tasks);
            break;
        case "event":
            eventCommand(parsedInput[DESCRIPTION_INDEX], tasks);
            break;
        default:
            System.out.println("Invalid command!" + System.lineSeparator());
            break;
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];
        Scanner in = new Scanner(System.in);

        printIntro();

        while (true) {
            System.out.print(PROMPT);
            String input = in.nextLine();
            executeCommand(input, tasks);
        }
    }
}
