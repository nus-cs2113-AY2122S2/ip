package solana;

import solana.task.Deadline;
import solana.task.Event;
import solana.task.Task;
import solana.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public static final int TASK_TYPE_INDEX = 0;
    public static final int TASK_INDEX = 3;
    public static final int STARTING_DESCRIPTION_INDEX = 2;

    public static final String FOLDER_PATH = "./data/";
    public static final String FILE_PATH = "./data/savedTasks.txt";

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
            System.out.println(task);
            listNumber++;
        }
    }

    public static void printAddedPrompt(Task newTask) {
        System.out.print("Added: ");
        System.out.println(newTask);

        if (tasks.size() > 1) {
            System.out.println("You now have " + tasks.size() + " tasks in the list" + System.lineSeparator());
        } else {
            System.out.println("You now have " + tasks.size() + " task in the list" + System.lineSeparator());
        }
    }

    public static void printDeletedPrompt(int taskIndex) {
        Task toBeDeleted = tasks.get(taskIndex);
        System.out.print("Deleted: ");
        System.out.println(toBeDeleted);
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
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskIndex + CONVERT_TASK_TO_I));
        } catch (NumberFormatException e) {
            System.out.println("Input a task number!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("No such task number!");
        }

        saveTasks();
        System.out.print(System.lineSeparator());
    }

    public static void markCommand(String parsedInput) {
        try {
            int taskIndex = Integer.parseInt(parsedInput);
            tasks.get(taskIndex + CONVERT_TASK_TO_I).markAsDone();
            System.out.println("Nice, I've marked this task as done:");
            System.out.println(tasks.get(taskIndex + CONVERT_TASK_TO_I));
        } catch (NumberFormatException e) {
            System.out.println("Input a task number!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("No such task number!");
        }

        saveTasks();
        System.out.print(System.lineSeparator());
    }

    public static void todoCommand(String parsedInput, boolean isFromUser, boolean isMarkNeeded) {
        Todo newTodo = new Todo(parsedInput);
        tasks.add(newTodo);

        if (isFromUser) {
            saveTasks();
            printAddedPrompt(newTodo);
        }

        if (isMarkNeeded) {
            newTodo.markAsDone();
        }
    }

    public static void deadlineCommand(String parsedInput, boolean isFromUser, boolean isMarkNeeded) {
        try {
            String[] parsedInputAsArray;
            Deadline newDeadline;

            if (isFromUser) {
                parsedInputAsArray = parsedInput.split(" /by ", SPLIT_LIMIT);
                newDeadline = new Deadline(parsedInputAsArray[DEADLINE_INDEX], parsedInputAsArray[BY_INDEX]);
            } else {
                parsedInputAsArray = parsedInput.split(" \\(By: ", SPLIT_LIMIT);
                newDeadline = new Deadline(parsedInputAsArray[DEADLINE_INDEX],
                        parsedInputAsArray[BY_INDEX].replace(")", ""));
            }

            tasks.add(newDeadline);

            if (isFromUser) {
                printAddedPrompt(newDeadline);
                saveTasks();
            }

            if (isMarkNeeded) {
                newDeadline.markAsDone();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Include the date or time using the keyword \"/by\"!" + System.lineSeparator());
        }
    }

    public static void eventCommand(String parsedInput, boolean isFromUser, boolean isMarkNeeded) {
        try {
            String[] parsedInputAsArray;
            Event newEvent;

            if (isFromUser) {
                parsedInputAsArray = parsedInput.split(" /at ", SPLIT_LIMIT);
                newEvent = new Event(parsedInputAsArray[EVENT_INDEX], parsedInputAsArray[AT_INDEX]);
            } else {
                parsedInputAsArray = parsedInput.split(" \\(At: ", SPLIT_LIMIT);
                newEvent = new Event(parsedInputAsArray[EVENT_INDEX],
                        parsedInputAsArray[AT_INDEX].replace(")", ""));
            }

            tasks.add(newEvent);

            if (isFromUser) {
                printAddedPrompt(newEvent);
                saveTasks();
            }

            if (isMarkNeeded) {
                newEvent.markAsDone();
            }
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

        saveTasks();
        System.out.print(System.lineSeparator());
    }

    public static void parseInputFromFile(String[] taskAsArray) {
        switch(taskAsArray[TASK_TYPE_INDEX]) {
        case "[T][":
            todoCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    false, false);
            break;
        case "[T][X]":
            todoCommand(taskAsArray[DESCRIPTION_INDEX], false, true);
            break;
        case "[D][":
            deadlineCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    false, false);
            break;
        case "[D][X]":
            deadlineCommand(taskAsArray[DESCRIPTION_INDEX], false, true);
            break;
        case "[E][":
            eventCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    false, false);
            break;
        case "[E][X]":
            eventCommand(taskAsArray[DESCRIPTION_INDEX], false, true);
            break;
        default:
            System.out.println("Unable to identify task type!");
        }
    }

    public static void readFromFile(File savedTasks) throws FileNotFoundException {
        Scanner in = new Scanner(savedTasks);
        while (in.hasNext()) {
            String taskAsString = in.nextLine().substring(TASK_INDEX);
            String[] taskAsArray = taskAsString.split(" ", SPLIT_LIMIT);
            parseInputFromFile(taskAsArray);
        }
    }

    public static void loadTasks() {
        File savedTasks = new File(FILE_PATH);
        if (savedTasks.exists()) {
            try {
                readFromFile(savedTasks);
            } catch (FileNotFoundException e) {
                System.out.println("savedTasks.txt not found!");
            }
        }
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        int listNumber = STARTING_LIST_NUMBER;
        for (Task task : tasks) {
            fw.write(listNumber + ". ");
            fw.write(task + System.lineSeparator());
            listNumber++;
        }
        fw.close();
    }

    public static void saveTasks() {
        File dataFolder = new File(FOLDER_PATH);
        if (!dataFolder.exists()) {
            boolean isSuccessful = dataFolder.mkdir();
            if (!isSuccessful) {
                System.out.println("Create data folder failed!");
                return;
            }
        }

        File savedTasks = new File(FILE_PATH);
        try {
            savedTasks.createNewFile();
        } catch (IOException e) {
            System.out.println("Create savedTasks.txt failed!");
            return;
        }

        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Writing to savedTasks.txt failed!");
        }
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
            todoCommand(parsedInput[DESCRIPTION_INDEX], true, false);
            break;
        case "deadline":
            deadlineCommand(parsedInput[DESCRIPTION_INDEX], true, false);
            break;
        case "event":
            eventCommand(parsedInput[DESCRIPTION_INDEX], true, false);
            break;
        default:
            System.out.println("Invalid command!" + System.lineSeparator());
            break;
        }
    }

    public static void main(String[] args) {
        loadTasks();
        Scanner in = new Scanner(System.in);

        printIntro();

        while (true) {
            System.out.print(PROMPT);
            String input = in.nextLine();
            executeCommand(input);
        }
    }
}
