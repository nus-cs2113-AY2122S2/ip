package bim;

import bim.task.Task;
import bim.task.TaskList;

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

    private static final String LINE_SEPARATOR = "----------------------------------";
    private static final String LINE_INDENT = "\t";

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

    public void printTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

}
