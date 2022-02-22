package bim;

import bim.task.Task;

import java.util.ArrayList;

public class Ui {
    private static final String MESSAGE_GREETING_1 = "Hi! I'm Bim!";
    private static final String MESSAGE_GREETING_2 = "What can I do for you?";
    private static final String MESSAGE_GOODBYE = "See you soon!";
    private static final String MESSAGE_MARK_TASK = "Okie Dokie!";
    private static final String MESSAGE_UNMARK_TASK = "Oh no! Anyways..";
    private static final String MESSAGE_DELETE_TASK = "I have deleted a task!";
    private static final String MESSAGE_ADD_TASK = "I have added a new task!";
    private static final String MESSAGE_LIST_SIZE_1 = "You now have ";
    private static final String MESSAGE_LIST_SIZE_2 = " task(s)!";

    private static final String EMPTY_TASKLIST = "404 Not Found";
    private static final String ERROR_INDEX = "Invalid index!";
    private static final String ERROR_COMMAND = "I didn't understand that!";
    private static final String ERROR_COMMAND_ARG = "Check your arguments!";
    private static final String ERROR_DATA_FILE = "Check data file!";

    private static final String LINE_SEPARATOR = "----------------------------------";
    private static final String LINE_INDENT = "\t";
    private static final String LIST_DOT = ".";

    public Ui() {    }

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

    public void printAddTaskMessage(String taskInfo, int taskCount) {
        System.out.println(MESSAGE_ADD_TASK);
        System.out.println(LINE_INDENT + taskInfo);
        System.out.println(MESSAGE_LIST_SIZE_1 + taskCount + MESSAGE_LIST_SIZE_2);
    }

    public void printDeleteMessage(String taskInfo, int taskCount) {
        System.out.println(MESSAGE_DELETE_TASK);
        System.out.println(LINE_INDENT + taskInfo);
        System.out.println(MESSAGE_LIST_SIZE_1 + taskCount + MESSAGE_LIST_SIZE_2);
    }

    public void printTask(Task task) {
        System.out.println(LINE_INDENT + task);
    }

    public void printMarkTaskMessage() {
        System.out.println(MESSAGE_MARK_TASK);
    }

    public void printUnmarkTaskMessage() {
        System.out.println(MESSAGE_UNMARK_TASK);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printErrorInCommand() {
        System.out.println(ERROR_COMMAND_ARG);
    }

    public void printErrorInIndex() {
        System.out.println(ERROR_INDEX);
    }

    public void printErrorInDataFile() {
        System.out.println(ERROR_DATA_FILE);
    }

    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(EMPTY_TASKLIST);
        }
        int i = 1;
        for (Task t : tasks) {
            System.out.println(i + LIST_DOT + t);
            ++i;
        }
    }

}
