package commands;

import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;
import tasks.Task;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * Represents the user's command to mark a task as not yet done
 */
public class UnmarkCommand extends Command {
    protected int taskNumber = -1;
    public static final String COMMAND_WORD = "unmark";

    /**
     * Prepares the unmark task for execution by extracting the task number of the task to be unmarked
     *
     * @param userInput User's input string
     */
    public UnmarkCommand(String userInput) {
        try {
            taskNumber = extractTaskNumber(userInput);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Ui.MISSING_TASK_NUMBER_MESSAGE);
            Ui.showLine();
        } catch (NumberFormatException e) {
            System.out.println(Ui.INVALID_TASK_NUMBER_MESSAGE);
            Ui.showLine();
        }
    }

    private int extractTaskNumber(String userInput) throws ArrayIndexOutOfBoundsException, InputMismatchException {
        String[] splitUserInput = userInput.split(" ");
        return Integer.parseInt(splitUserInput[1]);
    }

    /**
     * Marks a task in TaskManager.tasks as not yet done
     * Updates the task status in the user's task list file in the user's hard disk
     *
     * @param taskManager Manages the user's task list
     * @param fileEditor Reads and writes from and to the user's task list file in the user's hard disk
     */
    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        boolean isEmptyTaskList = checkTaskListSize();
        if (isEmptyTaskList) {
            System.out.println(Ui.EMPTY_TASK_LIST_MESSAGE);
            Ui.showLine();
            return;
        }
        if (taskNumber == -1) {
            return;
        }
        Task taskToUnmark = null;
        try {
            taskToUnmark = taskManager.getTasks().get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.TASK_NUMBER_OUT_OF_RANGE_MESSAGE);
            Ui.showLine();
            return;
        }
        taskManager.getTasks().get(taskNumber - 1).markTaskAsUndone();
        System.out.println(Ui.UNMARK_TASK_MESSAGE);
        System.out.println(taskManager.getTasks().get(taskNumber - 1));
        Ui.showLine();
        try {
            fileEditor.updateFile(taskManager.getTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
