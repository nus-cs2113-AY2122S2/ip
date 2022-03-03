package marites;

import marites.exception.MaritesException;
import marites.exception.TaskListOutOfBoundsException;
import marites.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    /* STRING CONSTANTS */

    // Logo found in https://emojicombos.com/kaomoji
    private static final String INTRO_MESSAGE = "(งツ)ว\n" +
            "Hi, I'm Marites! I've heard so many things about you!\n" +
            "I have a lot of stories to share, but first, how can I help you?\n";
    private static final String EXIT_MESSAGE = "See you next time!";
    private static final String LIST_TASK_ITEM_FORMAT_STRING = "%d. %s\n";
    private static final String ADD_TASK_FORMAT_STRING =
            "Alright, task added:%n  %s%nYour list currently has %d tasks.\n";
    private static final String MARK_DONE_MESSAGE = "Good job on getting this done!  ";
    private static final String MARK_UNDONE_MESSAGE = "Okay, I've marked this as not yet done:";
    private static final String DELETE_FORMAT_STRING =
            "Alright, task deleted:%n  %s%nYour list currently has %d tasks.\n";
    private static final String SET_TASK_STATUS_FORMAT_STRING = "%s%n  %s\n";
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:";
    private static final String FIND_NO_TASKS_MESSAGE = "No matching tasks were in your list.";

    private Scanner scanner;
    public static final String READ_TASK_LIST_ERROR_MESSAGE = "WARNING: Error when reading saved task list; initializing with empty list";
    public static final String SAVE_TASK_LIST_ERROR_MESSAGE = "WARNING: Error occurred while saving task list";

    private final Scanner in;
    private final PrintStream out;

    public Ui(InputStream inputStream, PrintStream printStream) {
        this.in = new Scanner(inputStream);
        this.out = printStream;
    }


    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints a separator string.
     */
    public void showSeparator() {
        out.println("========================================");
    }

    /**
     * Prints an introduction message.
     */
    public void showIntroduction() {
        out.println(INTRO_MESSAGE);
        showSeparator();
    }

    /**
     * Prints an exit message.
     */
    public void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
        showSeparator();
    }

    /**
     * Prints an error message.
     * @param error A MaritesException representing the error.
     */
    public void showError(MaritesException error) {
        System.out.println(error.getErrorMessage());
    }

    public void showAddTaskMessage(Task task, int listLength) {
        out.printf(ADD_TASK_FORMAT_STRING, task, listLength);
    }

    public void showDeleteTaskMessage(Task task, int listLength) {
        out.printf(DELETE_FORMAT_STRING, task, listLength);
    }

    public void showSetTaskStatusMessage(Task task, boolean isSet) {
        out.printf(SET_TASK_STATUS_FORMAT_STRING,
                isSet ? MARK_DONE_MESSAGE : MARK_UNDONE_MESSAGE, task);
    }

    public void showTaskList(TaskList taskList) {
        for (int i = 1; i <= taskList.getTaskCount(); ++i) {
            try {
                out.printf(LIST_TASK_ITEM_FORMAT_STRING, i, taskList.getTaskByIndex(i-1));
            } catch (TaskListOutOfBoundsException e) {
                // This will not trigger
            }
        }
    }

    public void showFindResultMessage(TaskList taskList, ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            out.println(FIND_NO_TASKS_MESSAGE);
        } else {
            out.println(FIND_MESSAGE);
            for (Task task : matchingTasks) {
                out.printf(LIST_TASK_ITEM_FORMAT_STRING, taskList.getTaskIndex(task) + 1, task);
            }
        }
    }
}
