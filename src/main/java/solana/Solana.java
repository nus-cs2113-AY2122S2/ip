package solana;

import solana.task.Deadline;
import solana.task.Event;
import solana.task.Task;
import solana.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

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

    public static final int CONVERT_TASK_TO_I = -1;

    public static final int SPLIT_LIMIT = 2;

    public static final int STARTING_LIST_NUMBER = 1;

    public static ArrayList<Task> tasks = new ArrayList<>();

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

    public static void printTasks() {
        int listNumber = STARTING_LIST_NUMBER;
        for (Task task : tasks) {
            System.out.print(listNumber + ".");
            task.printDescription();
            listNumber++;
        }
    }

    public static void printAddedPrompt(Task newTask) {
        System.out.print("Added: ");
        newTask.printDescription();

        if (tasks.size() > 1) {
            System.out.println("You now have " + tasks.size() + " tasks in the list" + System.lineSeparator());
        } else {
            System.out.println("You now have " + tasks.size() + " task in the list" + System.lineSeparator());
        }
    }

    public static void printDeletedPrompt(int taskIndex) {
        Task toBeDeleted = tasks.get(taskIndex);
        System.out.print("Deleted: ");
        toBeDeleted.printDescription();
        tasks.remove(taskIndex);

        if (tasks.size() > 1) {
            System.out.println("You now have " + tasks.size() + " tasks in the list");
        } else {
            System.out.println("You now have " + tasks.size() + " task in the list");
        }

    }

    public static void listCommand() {
        if (tasks.size() == 0) {
            System.out.println("No tasks yet!");
        } else {
            System.out.println("Here are the tasks in your list:");
            printTasks();
        }
        System.out.print(System.lineSeparator());
    }

    public static void unmarkCommand(String parsedInput) {
        try {
            int taskIndex = Integer.parseInt(parsedInput);
            tasks.get(taskIndex + CONVERT_TASK_TO_I).unmarkAsDone();
            tasks.get(taskIndex + CONVERT_TASK_TO_I).printDescription();
        } catch (NumberFormatException e) {
            System.out.println("Input a task number!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("No such task number!");
        }

        System.out.print(System.lineSeparator());
    }

    public static void markCommand(String parsedInput) {
        try {
            int taskIndex = Integer.parseInt(parsedInput);
            tasks.get(taskIndex + CONVERT_TASK_TO_I).markAsDone();
            tasks.get(taskIndex + CONVERT_TASK_TO_I).printDescription();
        } catch (NumberFormatException e) {
            System.out.println("Input a task number!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("No such task number!");
        }

        System.out.print(System.lineSeparator());
    }

    public static void todoCommand(String parsedInput) {
        Todo newTodo = new Todo(parsedInput);
        tasks.add(newTodo);

        printAddedPrompt(newTodo);
    }

    public static void deadlineCommand(String parsedInput) {
        try {
            String[] parsedInputAsArray = parsedInput.split(" /by ", SPLIT_LIMIT);
            Deadline newDeadline = new Deadline(parsedInputAsArray[DEADLINE_INDEX], parsedInputAsArray[BY_INDEX]);
            tasks.add(newDeadline);
            printAddedPrompt(newDeadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Include the date or time using the keyword \"/by\"!" + System.lineSeparator());
        }
    }

    public static void eventCommand(String parsedInput) {
        try {
            String[] parsedInputAsArray = parsedInput.split(" /at ", SPLIT_LIMIT);
            Event newEvent = new Event(parsedInputAsArray[EVENT_INDEX], parsedInputAsArray[AT_INDEX]);
            tasks.add(newEvent);
            printAddedPrompt(newEvent);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Include the date or time using keyword \"/at\"!" + System.lineSeparator());
        }
    }

    public static void deleteCommand(String parsedInput) {
        try {
            int taskIndex = Integer.parseInt(parsedInput);
            printDeletedPrompt(taskIndex + CONVERT_TASK_TO_I);
        } catch (NumberFormatException e) {
            System.out.println("Input a task number!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("No such task number!");
        }

        System.out.print(System.lineSeparator());
    }

    public static void checkDescription(String[] parsedInput) throws SolanaException {
        if (parsedInput.length == 1) {
            switch (parsedInput[COMMAND_INDEX]) {
            case "bye":
                //Fallthrough
            case "list":
                return;
            case "mark":
                //Fallthrough
            case "delete":
                throw new SolanaException("Input a task number!");
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

    public static void executeCommand(String input) {
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
            listCommand();
            break;
        case "mark":
            markCommand(parsedInput[DESCRIPTION_INDEX]);
            break;
        case "unmark":
            unmarkCommand(parsedInput[DESCRIPTION_INDEX]);
            break;
        case "delete":
            deleteCommand(parsedInput[DESCRIPTION_INDEX]);
            break;
        case "todo":
            todoCommand(parsedInput[DESCRIPTION_INDEX]);
            break;
        case "deadline":
            deadlineCommand(parsedInput[DESCRIPTION_INDEX]);
            break;
        case "event":
            eventCommand(parsedInput[DESCRIPTION_INDEX]);
            break;
        default:
            System.out.println("Invalid command!" + System.lineSeparator());
            break;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        printIntro();

        while (true) {
            System.out.print(PROMPT);
            String input = in.nextLine();
            executeCommand(input);
        }
    }
}
