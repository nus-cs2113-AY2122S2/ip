import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Marites {

    /* STRING CONSTANTS */

    // Logo found in https://emojicombos.com/kaomoji
    private static final String INTRO_MESSAGE = """
            (งツ)ว
            "Hi, I'm Marites! I've heard so many things about you!
            "I have a lot of stories to share, but first, how can I help you?
            """;
    private static final String EXIT_MESSAGE = "See you next time!";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String LIST_TASK_ITEM_FORMAT_STRING = "%d. %s%n";
    private static final String ADD_TASK_FORMAT_STRING = "added: %s%n";
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
     * Parses a user's command, and executes it.
     * @param userInput A line of the user's input.
     * @return Output that must be passed back to the user.
     */
    private static String processUserCommand(String userInput) {
        String[] tokens = userInput.split("\\s");
        if (userInput.equals(COMMAND_EXIT)) {
            return executeExit();
        } else if (userInput.equals(COMMAND_LIST)) {
            return executeListTasks();
        } else if (tokens[0].equals(COMMAND_MARK)) {
            return executeSetTaskStatus(tokens, true);
        } else if (tokens[0].equals(COMMAND_UNMARK)) {
            return executeSetTaskStatus(tokens, false);
        } else {
            return executeAddTask(userInput);
        }
    }

    /*
        The following methods take in either a
            - String, denoting the full line of user input, or
            - a String[], denoting the tokenized version of the user input.
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
     * Executes an add task command.
     * @param userInput The user's input.
     * @return A feedback string with the added task.
     */
    private static String executeAddTask(String userInput) {
        tasks.add(new Task(userInput));
        return String.format(ADD_TASK_FORMAT_STRING, userInput);
    }

    /**
     * Executes a set task command.
     * This can be used to both mark and unmark a task as done.
     * @param tokens The tokenized form of the user's input.
     * @param isDone The new done/undone status of the task.
     * @return A feedback string with the tasks' new status.
     */
    private static String executeSetTaskStatus(String[] tokens, boolean isDone) {
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
