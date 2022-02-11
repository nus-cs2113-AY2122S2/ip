package bim;

import bim.task.Deadline;
import bim.task.Event;
import bim.task.Task;
import bim.task.ToDo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Bim {
    private static final Scanner in = new Scanner(System.in);
    private static final ArrayList<Task> taskStore = new ArrayList<Task>();

    private static final String EMPTY_TASKLIST = "404 Not Found";
    private static final String ERROR_INDEX = "Invalid index!";
    private static final String ERROR_COMMAND = "I didn't understand that!";
    private static final String ERROR_COMMAND_ARG = "Check your arguments!";
    private static final String ERROR_DATA_FILE = "Check data file!";

    private static final String MESSAGE_GREETING_1 = "Hi! I'm Bim!";
    private static final String MESSAGE_GREETING_2 = "What can I do for you?";
    private static final String MESSAGE_GOODBYE = "See you soon!";
    private static final String MESSAGE_MARK_TASK = "Okie Dokie!";
    private static final String MESSAGE_UNMARK_TASK = "Oh no! Anyways..";

    private static final String DELIMITER_EVENT = " /at ";
    private static final String DELIMITER_DEADLINE = " /by ";

    private static final String OP_MARK = "mark";
    private static final String OP_UNMARK = "unmark";
    private static final String OP_ADD_DEADLINE = "deadline";
    private static final String OP_ADD_TODO = "todo";
    private static final String OP_ADD_EVENT = "event";
    private static final String OP_EXIT_PROGRAM = "bye";
    private static final String OP_LIST_TASK = "list";

    private static final String LINE_SEPARATOR = "----------------------------------";
    private static final String DATA_FILE_SEPARATOR = "|";
    private static final String DATA_FILE_NEW_LINE = "\n";
    private static final String DATA_FILE_DEADLINE = "D";
    private static final String DATA_FILE_EVENT = "E";
    private static final String DATA_FILE_TODO = "T";
    private static final int DATA_FILE_UNMARKED_TASK = 0;
    private static final int DATA_FILE_MARKED_TASK = 1;
    private static final int EXPECTED_ARG_NUMBER = 2;

    public static void main(String[] args) {
        printWelcomeMessage();
        loadDataFile();
        readInput();
        exitProgram();
    }

    public static void readInput() {
        boolean isExit = false;
        while (in.hasNextLine()) {
            String input = in.nextLine();
            String[] words = input.split(" ", 2);
            String command = words[0].toLowerCase();
            String commandArg = (words.length > 1) ? words[1] : "";
            try {
                isExit = handleCommand(command, commandArg);
            } catch (BimException exception) {
                System.out.println(ERROR_COMMAND);
            }
            System.out.println(LINE_SEPARATOR);
            if (isExit == true) {
                return;
            }
        }
    }

    private static boolean handleCommand(String command, String commandArg) throws BimException {
        switch (command) {
        case OP_LIST_TASK:
            listTask();
            break;
        case OP_MARK:
            modifyTask(OP_MARK, commandArg);
            break;
        case OP_UNMARK:
            modifyTask(OP_UNMARK, commandArg);
            break;
        case OP_ADD_TODO:
            addToDo(commandArg);
            break;
        case OP_ADD_DEADLINE:
            addDeadline(commandArg);
            break;
        case OP_ADD_EVENT:
            addEvent(commandArg);
            break;
        case OP_EXIT_PROGRAM:
            return true;
        default:
            throw new BimException();
        }
        return false;
    }

    private static void exitProgram() {
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(LINE_SEPARATOR);
    }

    private static void listTask() {
        if (taskStore.isEmpty()) {
            System.out.println(EMPTY_TASKLIST);
        }
        int i = 1;
        for (Task t : taskStore) {
            System.out.println(i + "." + t);
            ++i;
        }
    }

    private static void modifyTask(String mode, String commandArg) {
        int index = tryParseIndex(commandArg);
        if (!isValidIndex(index)) {
            System.out.println(ERROR_INDEX);
            return;
        }
        Task currentTask = taskStore.get(index);
        if (mode.equals(OP_MARK)) {
            currentTask.setAsDone();
            System.out.println(MESSAGE_MARK_TASK);
        } else {
            currentTask.setAsNotDone();
            System.out.println(MESSAGE_UNMARK_TASK);
        }
        System.out.println("\t" + currentTask);
    }

    private static void addToDo(String commandArg) {
        if (!isValidArgument(commandArg)) {
            System.out.println(ERROR_COMMAND_ARG);
            return;
        }
        ToDo newToDo = new ToDo(commandArg);
        taskStore.add(newToDo);
        writeData(commandArg);
        printAddMessage(OP_ADD_TODO);
    }

    private static void addDeadline(String commandArg) {
        if (!isValidArgument(DELIMITER_DEADLINE, commandArg)) {
            System.out.println(ERROR_COMMAND_ARG);
            return;
        }
        String[] parsedTokens = commandArg.split(DELIMITER_DEADLINE);
        String deadlineDescription = parsedTokens[0];
        String deadlineDate = parsedTokens[1];
        Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
        taskStore.add(newDeadline);
        writeData(DATA_FILE_DEADLINE, deadlineDescription, deadlineDate);
        printAddMessage(OP_ADD_DEADLINE);
    }

    private static void addEvent(String commandArg) {
        if (!isValidArgument(DELIMITER_EVENT, commandArg)) {
            System.out.println(ERROR_COMMAND_ARG);
            return;
        }
        String[] parsedTokens = commandArg.split(DELIMITER_EVENT);
        String eventDescription = parsedTokens[0];
        String eventDate = parsedTokens[1];
        Event newEvent = new Event(eventDescription, eventDate);
        taskStore.add(newEvent);
        writeData(DATA_FILE_EVENT, eventDescription, eventDate);
        printAddMessage(OP_ADD_EVENT);
    }

    private static boolean isValidArgument(String delimiter, String commandArg) {
        if (commandArg.contains(delimiter)) {
            String[] arguments = commandArg.split(delimiter);
            return arguments.length == EXPECTED_ARG_NUMBER;
        }
        return false;
    }

    private static boolean isValidArgument(String commandArg) {
        return !commandArg.isEmpty();
    }

    private static int tryParseIndex(String commandArg) {
        int index = -1;
        try {
            index = Integer.parseInt(commandArg);
        } catch (NumberFormatException exception) {
            return index;
        }
        return index;
    }

    private static boolean isValidIndex(int index) {
        return index < taskStore.size() && index >= 0;
    }

    private static void printWelcomeMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(MESSAGE_GREETING_1);
        System.out.println(MESSAGE_GREETING_2);
        System.out.println(LINE_SEPARATOR);
    }

    private static void printAddMessage(String type) {
        System.out.println("New " + type + " added!");
        System.out.println("\t" + taskStore.get(taskStore.size() - 1));
        System.out.println("There are " + taskStore.size() + " task(s) in the list");
    }

    private static void writeData(String type, String description, String date) {
        try {
            FileWriter writer = new FileWriter(getDataFilePath(), true);
            writer.write(type + DATA_FILE_SEPARATOR + DATA_FILE_UNMARKED_TASK + DATA_FILE_SEPARATOR + description + DATA_FILE_SEPARATOR + date + DATA_FILE_NEW_LINE);
            writer.close();
        } catch (IOException exception) {
            System.out.println(ERROR_DATA_FILE);
        }

    }

    private static void writeData(String description) {
        try {
            FileWriter writer = new FileWriter(getDataFilePath(), true);
            writer.write(DATA_FILE_TODO + DATA_FILE_SEPARATOR + DATA_FILE_UNMARKED_TASK + DATA_FILE_SEPARATOR + description + DATA_FILE_NEW_LINE);
            writer.close();
        } catch (IOException exception) {
            System.out.println(ERROR_DATA_FILE);
        }
    }

    private static void writeData(int index) {
        try {
            FileWriter writer = new FileWriter(getDataFilePath());
        } catch (IOException exception) {
            System.out.println(ERROR_DATA_FILE);
        }
    }

    private static void deleteData(int index) {
        try {
            FileWriter writer = new FileWriter(getDataFilePath());
        } catch (IOException exception) {
            System.out.println(ERROR_DATA_FILE);
        }
    }

    private static void loadDataFile() {
        File dataDirectory = new File(getDataDirectoryPath());
        if (dataDirectory.mkdir()) {
            File dataFile = new File(getDataFilePath());
            try {
                dataFile.createNewFile();
            } catch (IOException exception) {
                System.out.println("Something went wrong here!");
            }
        }
        else {
            // read file

        }
    }

    private static String getDataDirectoryPath() {
        return System.getProperty("user.dir").concat("\\data");
    }

    private static String getDataFilePath() {
        return getDataDirectoryPath().concat("\\bimData.txt");
    }
}
