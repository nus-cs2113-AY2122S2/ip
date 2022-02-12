package bim;

import bim.task.Deadline;
import bim.task.Event;
import bim.task.Task;
import bim.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bim {
    private static final Scanner in = new Scanner(System.in);
    private static final ArrayList<Task> taskStore = new ArrayList<Task>();
    private static final File dataDirectory = new File(getDataDirectoryPath());
    private static final File dataFile = new File(getDataFilePath());

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
    private static final String MESSAGE_DELETE_TASK = "Sure, deleted!";
    private static final String MESSAGE_ADD_TASK_1 = "New ";
    private static final String MESSAGE_ADD_TASK_2 = " added!";
    private static final String MESSAGE_LIST_SIZE_1 = "There are ";
    private static final String MESSAGE_LIST_SIZE_2 = " task(s) in the list";

    private static final String DELIMITER_EVENT = " /at ";
    private static final String DELIMITER_DEADLINE = " /by ";
    private static final String DELIMITER_DATA = " \\| ";

    private static final String OP_MARK = "mark";
    private static final String OP_UNMARK = "unmark";
    private static final String OP_ADD_DEADLINE = "deadline";
    private static final String OP_ADD_TODO = "todo";
    private static final String OP_ADD_EVENT = "event";
    private static final String OP_EXIT_PROGRAM = "bye";
    private static final String OP_LIST_TASK = "list";
    private static final String OP_DELETE_TASK = "delete";

    private static final String LINE_SEPARATOR = "----------------------------------";
    private static final String LINE_INDENT = "\t";
    private static final String LIST_DOT = ".";

    private static final String DATA_FOLDER_NAME = "data";
    private static final String DATA_FILE_NAME = "bimData.txt";
    private static final String DATA_FILE_SEPARATOR = " | ";
    private static final String DATA_FILE_NEW_LINE = "\n";
    private static final String DATA_FILE_DEADLINE = "D";
    private static final String DATA_FILE_EVENT = "E";
    private static final String DATA_FILE_TODO = "T";
    private static final String DATA_FILE_UNMARKED_TASK = "0";
    private static final String DATA_FILE_MARKED_TASK = "1";

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
            System.out.println(LINE_SEPARATOR);
            try {
                isExit = handleCommand(command, commandArg);
            } catch (BimException exception) {
                System.out.println(ERROR_COMMAND);
            }
            if (isExit == true) {
                return;
            }
            System.out.println(LINE_SEPARATOR);
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
        case OP_DELETE_TASK:
            deleteTask(commandArg);
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
            return;
        }
        int i = 1;
        for (Task t : taskStore) {
            System.out.println(i + LIST_DOT + t);
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
        modifyData(mode, index);
        System.out.println(LINE_INDENT + currentTask);
    }

    private static void deleteTask(String commandArg) {
        int index = tryParseIndex(commandArg);
        if (!isValidIndex(index)) {
            System.out.println(ERROR_INDEX);
            return;
        }
        String taskInfo = taskStore.get(index).toString();
        taskStore.remove(index);
        deleteData(index);
        printDeleteMessage(taskInfo);
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
        int index;
        try {
            index = Integer.parseInt(commandArg) - 1;
        } catch (NumberFormatException exception) {
            index = -1;
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

    private static void printDeleteMessage(String taskInfo) {
        System.out.println(MESSAGE_DELETE_TASK);
        System.out.println(LINE_INDENT + taskInfo);
        printListSize();
    }

    private static void printAddMessage(String type) {
        System.out.println(MESSAGE_ADD_TASK_1 + type + MESSAGE_ADD_TASK_2);
        System.out.println(LINE_INDENT + taskStore.get(taskStore.size() - 1));
        printListSize();
    }

    private static void printListSize() {
        System.out.println(MESSAGE_LIST_SIZE_1 + taskStore.size() + MESSAGE_LIST_SIZE_2);
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

    private static void modifyData(String mode, int index) {
        try {
            Scanner dataReader = new Scanner(dataFile);
            String dataFileContent = "";
            int i = 0;
            while (dataReader.hasNextLine()) {
                String currentLine = dataReader.nextLine();
                if (i == index) {
                    String[] currentParts = currentLine.split(DELIMITER_DATA);
                    if (mode.equals(OP_MARK)) {
                        currentParts[1] = DATA_FILE_MARKED_TASK;
                    }
                    else {
                        currentParts[1] = DATA_FILE_UNMARKED_TASK;
                    }
                    currentLine = String.join(DATA_FILE_SEPARATOR, currentParts);
                }
                ++i;
                dataFileContent += currentLine + DATA_FILE_NEW_LINE;
            }
            FileWriter writer = new FileWriter(getDataFilePath());
            writer.write(dataFileContent);
            dataReader.close();
            writer.close();

        } catch (IOException exception) {
            System.out.println(ERROR_DATA_FILE);
        }
    }

    private static void deleteData(int index) {
        try {
            Scanner dataReader = new Scanner(dataFile);
            String dataFileContent = "";
            int i = 0;

            while (dataReader.hasNextLine()) {
                if (i != index) {
                    dataFileContent += dataReader.nextLine() + DATA_FILE_NEW_LINE;
                }
                ++i;
            }
            FileWriter writer = new FileWriter(getDataFilePath());
            writer.write(dataFileContent);
            dataReader.close();
            writer.close();
        } catch (IOException exception) {
            System.out.println(ERROR_DATA_FILE);
        }
    }

    private static void loadDataFile() {
        if (doesDataFileExists()) {
            try {
                Scanner dataReader = new Scanner(dataFile);
                while (dataReader.hasNextLine()) {
                    String task = dataReader.nextLine();
                    String[] taskParts = task.split(DELIMITER_DATA);
                    Task newTask;

                    switch (taskParts[0]) {
                    case DATA_FILE_EVENT:
                        newTask = new Event(taskParts[2], taskParts[3]);
                        break;
                    case DATA_FILE_DEADLINE:
                        newTask = new Deadline(taskParts[2], taskParts[3]);
                        break;
                    default:
                        newTask = new ToDo(taskParts[2]);
                        break;
                    }
                    if (taskParts[1].equals(DATA_FILE_MARKED_TASK)) {
                        newTask.setAsDone();
                    }
                    taskStore.add(newTask);
                }
                dataReader.close();
            } catch (FileNotFoundException exception) {
                System.out.println(ERROR_DATA_FILE);
            }
        }

        else {
            initDataFile();
        }
    }

    private static boolean doesDataFileExists() {
        return dataDirectory.exists() && dataFile.exists();
    }

    private static void initDataFile() {
        try {
            dataDirectory.mkdir();
            dataFile.createNewFile();
        } catch (IOException exception) {
            System.out.println(ERROR_DATA_FILE);
        }
    }

    private static String getDataDirectoryPath() {
        return System.getProperty("user.dir") + "\\" + DATA_FOLDER_NAME;
    }

    private static String getDataFilePath() {
        return getDataDirectoryPath() + "\\" + DATA_FILE_NAME;
    }

}
