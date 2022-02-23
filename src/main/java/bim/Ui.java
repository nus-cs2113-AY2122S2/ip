package bim;

import bim.task.Task;
import bim.task.TaskList;

/**
 * Displays any message Bim has for the user like welcome greetings, goodbye messages, etc.
 */
public class Ui {
    private static final String MESSAGE_GREETING_1 = "Hi! I'm Bim!";
    private static final String MESSAGE_GREETING_2 = "What can I do for you?";
    private static final String MESSAGE_GOODBYE = "See you soon!";
    private static final String MESSAGE_MARK_TASK = "Task has been marked. Well done!";
    private static final String MESSAGE_UNMARK_TASK = "Task has been unmarked. Keep going!";
    private static final String MESSAGE_DELETE_TASK = "I have deleted a task!";
    private static final String MESSAGE_ADD_TASK = "I have added a new task!";
    private static final String MESSAGE_LIST_SIZE_1 = "You now have ";
    private static final String MESSAGE_LIST_SIZE_2 = " task(s)!";
    private static final String MESSAGE_LIST_TASK = "Listing all tasks!";
    private static final String NONEMPTY_SEARCH_RESULT = "These are the tasks containing your keyword!";
    private static final String EMPTY_SEARCH_RESULT = "Sorry, I could not find any matching task!";


    private static final String LINE_SEPARATOR = "----------------------------------";
    private static final String LINE_INDENT = "\t";

    private static final String ERROR_MARKER = "Error: ";

    public Ui() {
    }

    public void printWelcomeMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(MESSAGE_GREETING_1);
        System.out.println(MESSAGE_GREETING_2);
        System.out.println(LINE_SEPARATOR);
    }

    public void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    public void printExitMessage() {
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(LINE_SEPARATOR);
    }

    public void printAddTaskMessage(Task deletedTask, int taskCount) {
        System.out.println(MESSAGE_ADD_TASK);
        System.out.println(LINE_INDENT + deletedTask);
        System.out.println(MESSAGE_LIST_SIZE_1 + taskCount + MESSAGE_LIST_SIZE_2);
    }

    public void printDeleteMessage(Task deletedTask, int taskCount) {
        System.out.println(MESSAGE_DELETE_TASK);
        System.out.println(LINE_INDENT + deletedTask);
        System.out.println(MESSAGE_LIST_SIZE_1 + taskCount + MESSAGE_LIST_SIZE_2);
    }

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
     * Prints all the tasks in the task list using the <code>toString</code> method of TaskList
     *
     * @param tasks
     */
    public void printTaskList(TaskList tasks) {
        System.out.println(MESSAGE_LIST_TASK);
        System.out.println(tasks);
    }

}
