package bob.util.controller;

import bob.util.exception.BobInvalidLoad;
import bob.util.task.Deadlines;
import bob.util.task.Events;
import bob.util.task.Task;
import bob.util.task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {
    public static final String FILEPATH = "data/data.txt";
    public static final String MESSAGE_IO_ERROR = "File IO error occurred.";
    public static final String MESSAGE_LOAD_ERROR = "Load Error: ";
    public static final String MESSAGE_LOAD_STOPPED = "Loading data terminated";
    public static final String MESSAGE_INVALID_VALUES = "Unknown values detected.";
    public static final String MESSAGE_INVALID_LENGTH = "Invalid task length";

    public static final String DELIMIT_LOAD_DEADLINE = " by: ";
    public static final String DELIMIT_LOAD_EVENT = " at: ";

    public static final Integer MIN_TASK_LENGTH = 8;
    public static final Integer MIN_DETAILS_LENGTH = 7;
    public static final Integer TASK_INDICATOR = 1;
    public static final Integer TASK_INDICATOR_END = 2;
    public static final Integer STATUS_INDICATOR = 4;
    public static final Integer STATUS_INDICATOR_END = 5;
    public static final Integer TASK_DETAILS = 7;

    /**
     * Prints the stated error message due to saving or loading files.
     *
     * @param message an Error message.
     */
    public static void printLoadError(String message) {
        System.out.println("\t" + MESSAGE_LOAD_ERROR + message);
        System.out.println("\t" + MESSAGE_LOAD_STOPPED);
    }

    /**
     *  Ensures that the data directory and file has been created, else create them.
     *
     * @throws IOException when error met while creating the data file.
     */
    public static void checkFilePath() throws IOException {
        File file = new File(FILEPATH);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Stores each entry in the task list into a data file.
     *
     * @param list a Task class list.
     * @throws IOException when error met while writing to the data file.
     */
    public static void writeDataToFile(ArrayList<Task> list) throws IOException {
        FileWriter writer = new FileWriter(FILEPATH);
        for (int i = 0; i < list.size(); i++) {
            writer.write(String.valueOf(list.get(i)));
            writer.write(System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Saves the task list into a data file.
     *
     * @param list a Task class list.
     */
    public static void saveData(ArrayList<Task> list) {
        try {
            checkFilePath();
            writeDataToFile(list);
        } catch (IOException e) {
            printLoadError(MESSAGE_IO_ERROR);
        }
    }

    /**
     * Retrieve the boolean status of the task.
     *
     * @param taskStatusString the string used to infer the task's status.
     * @return Status of the task.
     * @throws BobInvalidLoad when the taskStatusString has unexpected values.
     */
    public static boolean getTaskStatus(String taskStatusString) throws BobInvalidLoad {
        if (taskStatusString.equals("X")) {
            return true;
        } else if (taskStatusString.equals(" ")) {
            return false;
        } else {
            throw new BobInvalidLoad();
        }
    }

    /**
     * Removes round brackets from a given text.
     *
     * @param text some text to be edited.
     * @return the text without round brackets.
     */
    public static String removeRoundBrackets(String text) {
        String result = text.replaceAll("[(){}]","");
        return result.trim();
    }

    /**
     * Retrieve the tokenized details of a deadline or event task.
     *
     * @param type    The type of task to be processed.
     * @param details The details of the task.
     * @return a list containing details of the task.
     * @throws BobInvalidLoad when the length of the string does not match minimum requirements.
     */
    public static String[] getTokenDetails(String type, String details) throws BobInvalidLoad {
        if (details.length() < MIN_DETAILS_LENGTH) {
            throw new BobInvalidLoad();
        }
        String delimiter;
        if (type.equals("D")) {
            delimiter = DELIMIT_LOAD_DEADLINE;
        }
        else {
            delimiter = DELIMIT_LOAD_EVENT;
        }
        String[] tokenDetails = Command.parseCommand(removeRoundBrackets(details), delimiter);
        if (tokenDetails[1] != null) {
            return tokenDetails;
        }
        throw new BobInvalidLoad();
    }

    /**
     * Returns an appropriate task made from details provided.
     *
     * @param type    The type of task to be created.
     * @param status  The status of the task.
     * @param details The details of the task.
     * @return A task object with aforementioned details.
     * @throws BobInvalidLoad when type of task is found to be invalid.
     */
    public static Task createTask(String type, boolean status, String details) throws BobInvalidLoad {
        Task task;
        String[] tokenDetails;
        switch (type) {
        case "T":
            task = new ToDos(details, status);
            break;
        case "D":
            tokenDetails = getTokenDetails(type, details);
            task = new Deadlines(tokenDetails[0], status, tokenDetails[1]);
            break;
        case "E":
            tokenDetails = getTokenDetails(type, details);
            task = new Events(tokenDetails[0], status, tokenDetails[1]);
            break;
        default:
            throw new BobInvalidLoad();
        }
        return task;
    }

    /**
     * Creates and appends a task to a list.
     *
     * @param list a Task class list.
     * @param data the data of the task to be created and appended.
     * @throws BobInvalidLoad when the length of the data does not meet minimum requirements.
     */
    public static void addToList(ArrayList<Task> list, String data) throws BobInvalidLoad {
        if (data.length() < MIN_TASK_LENGTH) {
            throw new BobInvalidLoad();
        }
        try {
            String taskType = data.substring(TASK_INDICATOR, TASK_INDICATOR_END);
            boolean taskStatus = getTaskStatus(data.substring(STATUS_INDICATOR, STATUS_INDICATOR_END));
            String taskDetails = data.substring(TASK_DETAILS);
            Task task = createTask(taskType, taskStatus, taskDetails);
            list.add(task);
        } catch (BobInvalidLoad e) {
            printLoadError(MESSAGE_INVALID_VALUES);
        }
    }

    /**
     * Reads data from a file and restores them into the list.
     *
     * @param list a Task class list to be restored.
     * @throws FileNotFoundException when the file cannot be found.
     */
    public static void readDataFromFile(ArrayList<Task> list) throws FileNotFoundException {
        try {
            File file = new File(FILEPATH);
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                addToList(list, in.nextLine());
            }
        } catch (BobInvalidLoad e) {
            printLoadError(MESSAGE_INVALID_LENGTH);
        }
    }

    /**
     * Loads data from a file back into the program's list.
     *
     * @param list a Task class list to be restored.
     */
    public static void loadData(ArrayList<Task> list) {
        try {
            checkFilePath();
            readDataFromFile(list);
        } catch (IOException e) {
            printLoadError(MESSAGE_IO_ERROR);
        }
    }
}
