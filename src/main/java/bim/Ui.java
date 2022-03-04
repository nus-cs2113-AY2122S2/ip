package bim;

import bim.task.Task;
import bim.task.TaskList;

import java.util.Scanner;

/**
 * Reads input from user. Display messages Bim has for the user like welcome greetings, goodbye messages, etc.
 */
public class Ui {
    private static final String MESSAGE_GREETING_1 = "Hi! I'm Bim!";
    private static final String MESSAGE_GREETING_2 = "What can I do for you?";
    private static final String MESSAGE_GOODBYE = "Have a great day!";
    private static final String MESSAGE_MARK_TASK = "Task has been marked. Well done!";
    private static final String MESSAGE_UNMARK_TASK = "Task has been unmarked. Keep going!";
    private static final String MESSAGE_DELETE_TASK = "I have deleted a task!";
    private static final String MESSAGE_ADD_TASK = "I have added a new task!";
    private static final String MESSAGE_LIST_SIZE_1 = "You now have ";
    private static final String MESSAGE_LIST_SIZE_2 = " task(s)!";
    private static final String MESSAGE_LIST_TASK = "Listing all tasks!";
    private static final String NONEMPTY_SEARCH_RESULT = "These are the tasks containing your keyword!";
    private static final String EMPTY_SEARCH_RESULT = "Sorry, I could not find any matching task!";
    private static final String LINE_SEPARATOR = "----------------------------------------";
    private static final String LINE_INDENT = "\t";
    private static final String ERROR_MARKER = "Error: ";

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the user input.
     * Inputs that are empty or blank are ignored.
     *
     * @return User input
     */
    public String readInput() {
        String input;

        do {
            input = scanner.nextLine();
        } while (input.isBlank());

        return input;
    }

    /**
     * Prints the welcome message when program starts
     */
    public void printWelcomeMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(MESSAGE_GREETING_1);
        System.out.println(MESSAGE_GREETING_2);
        System.out.println(LINE_SEPARATOR);
    }

    public void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the exit message when the program terminates
     */
    public void printExitMessage() {
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the task that is added to the task list.
     * @param addedTask The newly added task
     * @param taskCount The number of tasks in the task list
     */
    public void printAddTaskMessage(Task addedTask, int taskCount) {
        System.out.println(MESSAGE_ADD_TASK);
        System.out.println(LINE_INDENT + addedTask);
        System.out.println(MESSAGE_LIST_SIZE_1 + taskCount + MESSAGE_LIST_SIZE_2);
    }

    /**
     * Prints the task that was deleted from the task list
     * @param deletedTask The deleted task
     * @param taskCount The number of tasks in the task list left
     */
    public void printDeleteMessage(Task deletedTask, int taskCount) {
        System.out.println(MESSAGE_DELETE_TASK);
        System.out.println(LINE_INDENT + deletedTask);
        System.out.println(MESSAGE_LIST_SIZE_1 + taskCount + MESSAGE_LIST_SIZE_2);
    }

    /**
     * Prints the search result. If the result is empty, print an empty search result message instead.
     * @param result The search result from task list
     */
    public void printSearchResult(String result) {
        if (result.isEmpty()) {
            System.out.println(EMPTY_SEARCH_RESULT);
        }
        else {
            System.out.println(NONEMPTY_SEARCH_RESULT);
            System.out.println(result);
        }

    }

    public void printMarkTaskMessage(Task task) {
        System.out.println(MESSAGE_MARK_TASK);
        System.out.println(LINE_INDENT + task);
    }

    public void printUnmarkTaskMessage(Task task) {
        System.out.println(MESSAGE_UNMARK_TASK);
        System.out.println(LINE_INDENT + task);
    }

    /**
     * Prints an error message.
     * Used when throwing BimExceptions
     *
     * @param message
     */
    public void printErrorMessage(String message) {
        System.out.println(ERROR_MARKER + message);
    }

    /**
     * Prints all the tasks in the task list
     *
     * @param tasks The task list to be printed.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println(MESSAGE_LIST_TASK);
        System.out.println(tasks);
    }

}
