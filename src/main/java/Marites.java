import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Marites {

    /* STRING CONSTANTS */

    // Logo found in https://emojicombos.com/kaomoji
    private static final String INTRO_MESSAGE = "(งツ)ว\n" +
            "Hi, I'm Marites! I've heard so many things about you!\n" +
            "I have a lot of stories to share, but first, how can I help you?\n";
    private static final String EXIT_MESSAGE = "See you next time!";
    private static final String UNKNOWN_COMMAND_MESSAGE = "I didn't understand that.";

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_ADD_DEADLINE_TAG = "/by";
    private static final String COMMAND_ADD_EVENT_TAG = "/at";

    private static final String LIST_TASK_ITEM_FORMAT_STRING = "%d. %s%n";
    private static final String ADD_TASK_FORMAT_STRING =
        "Alright, task added:%n  %s%nYour list currently has %d tasks.%n";
    private static final String MARK_DONE_MESSAGE = "Good job on getting this done!";
    private static final String MARK_UNDONE_MESSAGE = "Okay, I've marked this as not yet done:";
    private static final String SET_TASK_STATUS_FORMAT_STRING = "%s%n    %s%n";

    private static final Scanner SCANNER = new Scanner(System.in);

    /** The task list being tracked by Marites. */
    private static final ArrayList<Task> tasks = new ArrayList<>();



    public static void main(String[] args) {
        printIntroduction();
        while (true) {
            String userInput = SCANNER.nextLine();
            String commandFeedback = processUserCommand(userInput);
            showFeedback(commandFeedback);
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
     * Get the type of command issued.
     * @param userInput The user's input
     * @return The command type.
     */
    private static String getCommandType(String userInput) {
        String[] tokens = userInput.split("\\s");
        return tokens[0];
    }

    /**
     * Parses a user's command, and executes it.
     * @param userInput A line of the user's input.
     * @return Output that must be passed back to the user.
     */
    private static String processUserCommand(String userInput) {
        switch (getCommandType(userInput)) {
        case COMMAND_EXIT:
            return executeExit();
        case COMMAND_LIST:
            return executeListTasks();
        case COMMAND_MARK:
            return executeSetTaskStatus(userInput, true);
        case COMMAND_UNMARK:
            return executeSetTaskStatus(userInput, false);
        case COMMAND_ADD_TODO:
        case COMMAND_ADD_DEADLINE:
        case COMMAND_ADD_EVENT:
            return executeAddTask(userInput);
        default:
            return UNKNOWN_COMMAND_MESSAGE;
        }
    }

    /*
        The following methods take in a String,
            denoting the full line of user input.
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
     * @param userInput The user's input
     * @return A Task object representing the task
     */
    private static Task parseAddTask(String userInput) {
        String[] tokens = userInput.split("\\s", 2);
        String[] parametersSplit;
        String commandType = tokens[0], commandParameters = tokens[1];
        switch (commandType) {
        case COMMAND_ADD_TODO:
            return new Todo(commandParameters.strip());
        case COMMAND_ADD_DEADLINE:
            parametersSplit = commandParameters.split(COMMAND_ADD_DEADLINE_TAG);
            return new Deadline(parametersSplit[0].strip(), parametersSplit[1].strip());
        case COMMAND_ADD_EVENT:
            parametersSplit = commandParameters.split(COMMAND_ADD_EVENT_TAG);
            return new Event(parametersSplit[0].strip(), parametersSplit[1].strip());
        default:
            return new Task("");
        }
    }

    /**
     * Executes an add task command.
     * @param userInput The user's input.
     * @return A feedback string with the added task.
     */
    private static String executeAddTask(String userInput) {
        Task task = parseAddTask(userInput);
        tasks.add(task);
        return String.format(ADD_TASK_FORMAT_STRING, task, tasks.size());
    }

    /**
     * Executes a set task command.
     * This can be used to both mark and unmark a task as done.
     * @param userInput The user's input.
     * @param isDone The new done/undone status of the task.
     * @return A feedback string with the tasks' new status.
     */
    private static String executeSetTaskStatus(String userInput, boolean isDone) {
        String[] tokens = userInput.split("\\s");
        int taskIndex = parseInt(tokens[1]);
        Task taskToMark = tasks.get(taskIndex - 1);
        taskToMark.setDone(isDone);
        String message = (isDone ? MARK_DONE_MESSAGE :
                MARK_UNDONE_MESSAGE);
        return String.format(SET_TASK_STATUS_FORMAT_STRING, message, taskToMark);
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
