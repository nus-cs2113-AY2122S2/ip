package solana;

import solana.task.Deadline;
import solana.task.Event;
import solana.task.Task;
import solana.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

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

    public static final int TASK_TYPE_INDEX = 0;
    public static final int TASK_INDEX = 3;
    public static final int STARTING_DESCRIPTION_INDEX = 2;

    public static final int MAX_TASKS = 100;

    public static final String FOLDER_PATH = "./data/";
    public static final String FILE_PATH = "./data/savedTasks.txt";

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
            System.out.println(tasks[i]);
        }
    }

    public static void printAddedPrompt(Task newTask) {
        System.out.print("Added: ");
        System.out.println(newTask);

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
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[taskIndex + CONVERT_TASK_TO_I]);
        } catch (NumberFormatException e) {
            System.out.println("Input a task number!");
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("No such task number!");
        }

        saveTasks(tasks);
        System.out.print(System.lineSeparator());
    }

    public static void markCommand(String parsedInput, Task[] tasks) {
        try {
            int taskIndex = Integer.parseInt(parsedInput);
            tasks[taskIndex + CONVERT_TASK_TO_I].markAsDone();
            System.out.println("Nice, I've marked this task as done:");
            System.out.println(tasks[taskIndex + CONVERT_TASK_TO_I]);
        } catch (NumberFormatException e) {
            System.out.println("Input a task number!");
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("No such task number!");
        }

        saveTasks(tasks);
        System.out.print(System.lineSeparator());
    }

    public static void todoCommand(String parsedInput, Task[] tasks, boolean isFromUser, boolean isMarkNeeded) {
        Todo newTodo = new Todo(parsedInput);
        tasks[Task.getTaskNum() + CONVERT_TASK_TO_I] = newTodo;

        if (isFromUser) {
            saveTasks(tasks);
            printAddedPrompt(newTodo);
        }

        if (isMarkNeeded) {
            newTodo.markAsDone();
        }
    }

    public static void deadlineCommand(String parsedInput, Task[] tasks, boolean isFromUser, boolean isMarkNeeded) {
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

            tasks[Task.getTaskNum() + CONVERT_TASK_TO_I] = newDeadline;

            if (isFromUser) {
                printAddedPrompt(newDeadline);
                saveTasks(tasks);
            }

            if (isMarkNeeded) {
                newDeadline.markAsDone();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Include the date or time using the keyword \"/by\"!" + System.lineSeparator());
        }
    }

    public static void eventCommand(String parsedInput, Task[] tasks, boolean isFromUser, boolean isMarkNeeded) {
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

            tasks[Task.getTaskNum() + CONVERT_TASK_TO_I] = newEvent;

            if (isFromUser) {
                printAddedPrompt(newEvent);
                saveTasks(tasks);
            }

            if (isMarkNeeded) {
                newEvent.markAsDone();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Include the date or time using keyword \"/at\"!" + System.lineSeparator());
        }
    }

    public static void loadTodo(String description, Task[] tasks, boolean isMarked) {
        todoCommand(description, tasks, false, isMarked);
    }

    public static void loadDeadline(String description, Task[] tasks, boolean isMarked) {
        deadlineCommand(description, tasks, false, isMarked);
    }

    public static void loadEvent(String description, Task[] tasks, boolean isMarked) {
        eventCommand(description, tasks, false, isMarked);
    }

    public static void parseInputFromFile(Task[] tasks, String[] taskAsArray) {
        switch(taskAsArray[TASK_TYPE_INDEX]) {
        case "[T][":
            todoCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    tasks, false, false);
            break;
        case "[T][X]":
            todoCommand(taskAsArray[DESCRIPTION_INDEX], tasks, false, true);
            break;
        case "[D][":
            deadlineCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    tasks, false, false);
            break;
        case "[D][X]":
            deadlineCommand(taskAsArray[DESCRIPTION_INDEX], tasks, false, true);
            break;
        case "[E][":
            eventCommand(taskAsArray[DESCRIPTION_INDEX].substring(STARTING_DESCRIPTION_INDEX),
                    tasks, false, false);
            break;
        case "[E][X]":
            eventCommand(taskAsArray[DESCRIPTION_INDEX], tasks, false, true);
            break;
        default:
            System.out.println("Unable to identify task type!");
        }
    }

    public static void readFromFile(Task[] tasks, File savedTasks) throws FileNotFoundException{
        Scanner in = new Scanner(savedTasks);
        while (in.hasNext()) {
            String taskAsString = in.nextLine().substring(TASK_INDEX);
            String[] taskAsArray = taskAsString.split(" ", SPLIT_LIMIT);
            parseInputFromFile(tasks, taskAsArray);
        }
    }

    public static void loadTasks(Task[] tasks) {
        File savedTasks = new File(FILE_PATH);
        if (savedTasks.exists()) {
            try {
                readFromFile(tasks, savedTasks);
            } catch (FileNotFoundException e) {
                System.out.println("savedTasks.txt not found!");
            }
        }
    }

    public static void writeToFile(Task[] tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < Task.getTaskNum(); i++) {
            fw.write(i + CONVERT_I_TO_TASK + ". ");
            fw.write(tasks[i].toString() + System.lineSeparator());
        }
        fw.close();
    }

    public static void saveTasks(Task[] tasks) {
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
            writeToFile(tasks);
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
            todoCommand(parsedInput[DESCRIPTION_INDEX], tasks, true, false);
            break;
        case "deadline":
            deadlineCommand(parsedInput[DESCRIPTION_INDEX], tasks, true, false);
            break;
        case "event":
            eventCommand(parsedInput[DESCRIPTION_INDEX], tasks, true, false);
            break;
        default:
            System.out.println("Invalid command!" + System.lineSeparator());
            break;
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];
        loadTasks(tasks);
        Scanner in = new Scanner(System.in);

        printIntro();

        while (true) {
            System.out.print(PROMPT);
            String input = in.nextLine();
            executeCommand(input, tasks);
        }
    }
}
