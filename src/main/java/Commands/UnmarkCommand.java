package Commands;

import Interfaces.UI;

import Managers.TaskManager;

/**
 * Command for Bao to mark a task in the task list as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates unmark command for specified task.
     *
     * @param index Index (in task list) of task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Calls <code>TaskManager</code> method to unmark task and displays the task that was unmarked.
     *
     * @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) throws IndexOutOfBoundsException, NumberFormatException {
        try {
            ui.unmarkMessage(taskManager.unmarkTask(index));
        } catch (Exception e) {
            throw e;
        }
    }
}
