package marites;

import marites.task.Deadline;
import marites.task.Event;
import marites.task.Task;
import marites.task.Todo;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;

import static java.lang.Integer.parseInt;

public class Marites {

    /* STRING CONSTANTS */

    // Logo found in https://emojicombos.com/kaomoji
    private static final String INTRO_MESSAGE = "(งツ)ว\n" +
            "Hi, I'm Marites! I've heard so many things about you!\n" +
            "I have a lot of stories to share, but first, how can I help you?\n";
    private static final String EXIT_MESSAGE = "See you next time!";
    private static final String UNKNOWN_COMMAND_MESSAGE = "I didn't understand that.\n";

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_ADD_DEADLINE_TAG = "--by";
    private static final String COMMAND_ADD_EVENT_TAG = "--at";
    private static final String COMMAND_DELETE = "delete";

    private static final String LIST_TASK_ITEM_FORMAT_STRING = "%d. %s\n";
    private static final String ADD_TASK_FORMAT_STRING =
           "Alright, task added:%n  %s%nYour list currently has %d tasks.\n";
    private static final String ADD_TASK_MISSING_PARAMETER_MESSAGE =
           "You're missing a parameter: %s\n";
    private static final String ADD_TASK_MISSING_DESCRIPTION_MESSAGE =
            "Please add a description to your task.\n";
    private static final String ADD_TASK_UNKNOWN_TASK_MESSAGE =
            "I don't know this task type: '%s'\n";
    private static final String MARK_DONE_MESSAGE = "Good job on getting this done!";
    private static final String MARK_UNDONE_MESSAGE = "Okay, I've marked this as not yet done:";
    private static final String DELETE_TASK_MESSAGE =
            "Alright, task deleted:%n  %s%nYour list currently has %d tasks.\n";
    private static final String INVALID_TASK_NUMBER_MESSAGE =
            "I either didn't get a task number, or couldn't understand it: '%s'\n";
    private static final String SET_TASK_STATUS_FORMAT_STRING = "%s%n    %s\n";

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String SAVE_FILENAME = "tasklist.ser";
    public static final String READ_TASK_LIST_ERROR_MESSAGE = "WARNING: Error when reading saved task list; initializing with empty list";
    public static final String SAVE_TASK_LIST_ERROR_MESSAGE = "WARNING: Error occurred while saving task list";

    /** The task list being tracked by marites.Marites. */
    private static ArrayList<Task> tasks = new ArrayList<>();



    public static void main(String[] args) {
        initializeTaskList();
        printIntroduction();
        while (true) {
            String userInput = SCANNER.nextLine();
            String commandFeedback = processUserCommand(userInput);
            showFeedback(commandFeedback);
        }
    }

    private static void initializeTaskList() {
        File saveFile = new File(SAVE_FILENAME);
        if (!saveFile.exists()) {
            tasks = new ArrayList<>();
            return;
        }
        try {
            FileInputStream fileIn = new FileInputStream(SAVE_FILENAME);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            tasks = (ArrayList<Task>)objIn.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println(READ_TASK_LIST_ERROR_MESSAGE);
            tasks = new ArrayList<>();
        }
    }

    private static void saveTaskList() {
        try {
            File saveFile = new File(SAVE_FILENAME);
            saveFile.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(SAVE_FILENAME);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(tasks);
        } catch (IOException e) {
            System.out.println(SAVE_TASK_LIST_ERROR_MESSAGE);
        }
    }

    /* OUTPUT */

    /**
     * Prints a separator string.
     */
    private static void printSeparator() {
        System.out.println("========================================");
    }

    /**
     * Prints an introductory message.
     */
    private static void printIntroduction() {
        System.out.println(INTRO_MESSAGE);
        printSeparator();
    }
    /**
     * Prints an exit message.
     */
    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
        printSeparator();
    }

    /**
     * Prints a feedback string from an executed command.
     * @param feedback The feedback string to execute.
     */
    private static void showFeedback(String feedback) {
        printSeparator();
        System.out.print(feedback);
        printSeparator();
    }

    /**
     * Divides a line of user input into the command type, and the command.
     * @param userInput The user's input
     * @return A String[] with length 2. The first element is the command type,
     *  while the second element is the command. If the command has no body
     *  (e.g. list, unmark), this second element is an empty string.
     */
    private static String[] splitCommandTypeAndCommand(String userInput) {
        String[] inputSplit = userInput.split("\\s", 2);
        return new String[]{inputSplit[0].strip(),
                (inputSplit.length > 1 ? inputSplit[1] : "").strip()};
    }

    /**
     * Parses a user's command, and executes it.
     * @param userInput A line of the user's input.
     * @return Output that must be passed back to the user.
     */
    private static String processUserCommand(String userInput) {
        String[] commandSplit = splitCommandTypeAndCommand(userInput);
        String commandOutput;
        String commandType = commandSplit[0], command = commandSplit[1];
        switch (commandType) {
        case COMMAND_EXIT:
            commandOutput = executeExit();
            break;
        case COMMAND_LIST:
            commandOutput = executeListTasks();
            break;
        case COMMAND_MARK:
            commandOutput = executeSetTaskStatus(command, true);
            break;
        case COMMAND_UNMARK:
            commandOutput = executeSetTaskStatus(command, false);
            break;
        case COMMAND_ADD_TODO:
        case COMMAND_ADD_DEADLINE:
        case COMMAND_ADD_EVENT:
            // All 3 cases fallthrough here
            commandOutput = executeAddTask(commandType, command);
            break;
        case COMMAND_DELETE:
            commandOutput = executeDelete(command);
            break;
        default:
            commandOutput = UNKNOWN_COMMAND_MESSAGE;
            break;
        }
        return commandOutput;
    }

    /*
        The following methods take in a String, denoting a command.
        They return a String, denoting the feedback after executing the command.
     */

    /**
     * Executes a list command.
     * @return The list of tasks.
     */
    private static String executeListTasks() {
        StringBuilder taskList = new StringBuilder();
        for (int i = 1; i <= tasks.size(); ++i) {
            String task = String.format(LIST_TASK_ITEM_FORMAT_STRING, i, tasks.get(i-1));
            taskList.append(task);
        }
        return taskList.toString();
    }

    /**
     * Parses an add task command given by the user.
     * @param taskType The task's type.
     * @param command The user's command.
     * @return A marites.task.Task object representing the task.
     */
    private static Task parseAddTask(String taskType, String command)
        throws EmptyTaskDescriptionException, MissingParameterException, UnknownTaskTypeException {
        String[] parametersSplit;
        if (command.length() == 0) {
            throw new EmptyTaskDescriptionException();
        }
        switch (taskType) {
        case COMMAND_ADD_TODO:
            return new Todo(command);
        case COMMAND_ADD_DEADLINE:
            parametersSplit = command.split(COMMAND_ADD_DEADLINE_TAG);
            if (parametersSplit.length == 1) {
                throw new MissingParameterException(COMMAND_ADD_DEADLINE_TAG);
            }
            return new Deadline(parametersSplit[0].strip(), parametersSplit[1].strip());
        case COMMAND_ADD_EVENT:
            parametersSplit = command.split(COMMAND_ADD_EVENT_TAG);
            if (parametersSplit.length == 1) {
                throw new MissingParameterException(COMMAND_ADD_EVENT_TAG);
            }
            return new Event(parametersSplit[0].strip(), parametersSplit[1].strip());
        default:
            throw new UnknownTaskTypeException(taskType);
        }
    }
    /**
     * Executes an add task command.
     * @param taskType The task's type.
     * @param command The user's command.
     * @return A feedback string with the added task.
     */
    private static String executeAddTask(String taskType, String command) {
        Task task;
        try {
            task = parseAddTask(taskType, command);
        } catch (MissingParameterException e) {
            return String.format(ADD_TASK_MISSING_PARAMETER_MESSAGE, e.getMissingParameterTag());
        } catch (EmptyTaskDescriptionException e) {
            return ADD_TASK_MISSING_DESCRIPTION_MESSAGE;
        } catch (UnknownTaskTypeException e) {
            return String.format(ADD_TASK_UNKNOWN_TASK_MESSAGE, e.getTaskType());
        }
        tasks.add(task);
        saveTaskList();
        return String.format(ADD_TASK_FORMAT_STRING, task, tasks.size());
    }

    /**
     * Executes a set task command.
     * This can be used to both mark and unmark a task as done.
     * @param command The user's command.
     * @param isDone The new done/undone status of the task.
     * @return A feedback string with the tasks' new status.
     */
    private static String executeSetTaskStatus(String command, boolean isDone) {
        int taskIndex;
        try {
            taskIndex = parseInt(command);
        } catch (NumberFormatException e) {
            return String.format(INVALID_TASK_NUMBER_MESSAGE, command);
        }
        Task taskToMark = tasks.get(taskIndex - 1);
        taskToMark.setDone(isDone);
        String message = (isDone ? MARK_DONE_MESSAGE :
                MARK_UNDONE_MESSAGE);
        saveTaskList();
        return String.format(SET_TASK_STATUS_FORMAT_STRING, message, taskToMark);
    }

    /**
     * Executes a delete command.
     * @param command The user's command (in this case, the index of the task to delete.)
     * @return A feedback string with the deleted task.
     */
    private static String executeDelete(String command) {
        int taskIndex;
        try {
            taskIndex = parseInt(command);
        } catch (NumberFormatException e) {
            return String.format(INVALID_TASK_NUMBER_MESSAGE, command);
        }
        Task taskToDelete = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        saveTaskList();
        return String.format(DELETE_TASK_MESSAGE, taskToDelete, tasks.size());
    }

    /**
     * Executes an exit command.
     * This method calls System.exit, so it should not return.
     * @return A dummy value.
     */
    private static String executeExit() {
        printExitMessage();
        System.exit(0);
        return "";
    }
}
