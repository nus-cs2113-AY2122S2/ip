package solana;

import solana.task.Deadline;
import solana.task.Event;
import solana.task.Task;
import solana.task.Todo;

import java.util.Scanner;

public class Solana {
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";
    public static final String RESET_COLOUR = "\033[0m";
    public static final String PROMPT = CYAN_BOLD_BRIGHT + "> " + RESET_COLOUR;

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
        String logo = "  __|   _ \\  |       \\     \\ |    \\\n"
                + "\\__ \\  (   | |      _ \\   .  |   _ \\\n"
                + "____/ \\___/ ____| _/  _\\ _|\\_| _/  _\\\n";

        System.out.print(System.lineSeparator());
        System.out.println(CYAN_BOLD_BRIGHT + logo + RESET_COLOUR);
        System.out.println("Hi, I'm Solana");
        System.out.println("What can I do for you?" + System.lineSeparator());
    }

    public static String[] parseInput(String input) {
        String[] inputAsArray = input.split(" ", SPLIT_LIMIT);
        return inputAsArray;
    }

    public static void printTasks(Task[] tasks) {
        for (int i = 0; i < Task.getTaskNum(); i++) {
            System.out.print(i + CONVERT_I_TO_TASK + ".");
            tasks[i].printDescription();
        }
    }

    public static void printAddedPrompt(Task newTask) {
        System.out.print("Added: ");
        newTask.printDescription();

        if (Task.getTaskNum() > 1) {
            System.out.println("You now have " + Task.getTaskNum() + " tasks in the list" + System.lineSeparator());
        } else {
            System.out.println("You now have " + Task.getTaskNum() + " task in the list" + System.lineSeparator());
        }
    }

    public static void listCommand(Task[] tasks) {
        if (Task.getTaskNum() == 0) {
            System.out.println("No tasks yet!");
        } else {
            System.out.println("Here are the tasks in your list:");
            printTasks(tasks);
        }
        System.out.print(System.lineSeparator());
    }

    public static void unmarkCommand(String parsedInput, Task[] tasks) {
        try {
            int taskIndex = Integer.parseInt(parsedInput);
            tasks[taskIndex + CONVERT_TASK_TO_I].unmarkAsDone();
            tasks[taskIndex + CONVERT_TASK_TO_I].printDescription();
        } catch (NumberFormatException e) {
            System.out.println("Input a task number!");
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("No such task number!");
        }

        System.out.print(System.lineSeparator());
    }

    public static void markCommand(String parsedInput, Task[] tasks) {
        try {
            int taskIndex = Integer.parseInt(parsedInput);
            tasks[taskIndex + CONVERT_TASK_TO_I].markAsDone();
            tasks[taskIndex + CONVERT_TASK_TO_I].printDescription();
        } catch (NumberFormatException e) {
            System.out.println("Input a task number!");
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("No such task number!");
        }

        System.out.print(System.lineSeparator());
    }

    public static void todoCommand(String parsedInput, Task[] tasks) {
        Todo newTodo = new Todo(parsedInput);
        tasks[Task.getTaskNum() + CONVERT_TASK_TO_I] = newTodo;

        printAddedPrompt(newTodo);
    }

    public static void deadlineCommand(String parsedInput, Task[] tasks) {
        try {
            String[] parsedInputAsArray = parsedInput.split(" /by ", SPLIT_LIMIT);
            Deadline newDeadline = new Deadline(parsedInputAsArray[DEADLINE_INDEX], parsedInputAsArray[BY_INDEX]);
            tasks[Task.getTaskNum() + CONVERT_TASK_TO_I] = newDeadline;
            printAddedPrompt(newDeadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Include the date or time using the keyword \"/by\"!" + System.lineSeparator());
        }
    }

    public static void eventCommand(String parsedInput, Task[] tasks) {
        try {
            String[] parsedInputAsArray = parsedInput.split(" /at ", SPLIT_LIMIT);
            Event newEvent = new Event(parsedInputAsArray[EVENT_INDEX], parsedInputAsArray[AT_INDEX]);
            tasks[Task.getTaskNum() + CONVERT_TASK_TO_I] = newEvent;
            printAddedPrompt(newEvent);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Include the date or time using keyword \"/at\"!" + System.lineSeparator());
        }
    }

    public static void checkDescription(String[] parsedInput) throws SolanaException {
        if (parsedInput.length == 1) {
            switch (parsedInput[COMMAND_INDEX]) {
            case "bye":
                //Fallthrough
            case "list":
                return;
            case "todo":
                throw new SolanaException("Description of todo cannot be empty!");
            case "deadline":
                throw new SolanaException("Description of deadline cannot be empty!");
            case "event":
                throw new SolanaException("Description of event cannot be empty!");
            default:
                throw new SolanaException("Invalid command!");
            }
        }
    }

    public static void executeCommand(String input, Task[] tasks) {
        String[] parsedInput = parseInput(input);

        try {
            checkDescription(parsedInput);
        } catch (SolanaException e){
            return;
        }

        switch (parsedInput[COMMAND_INDEX]) {
        case "bye":
            System.out.println("Goodbye!");
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
