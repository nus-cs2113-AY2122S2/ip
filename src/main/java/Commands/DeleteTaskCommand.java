package Commands;

import Components.Task;

import Interfaces.UI;

import Managers.TaskManager;

/**
 * Command for Bao to remove a task from the task list.
 */
public class DeleteTaskCommand extends Command {
    private int index;

    /**
     * Creates delete command for specified task.
     *
     * @param index Index (in task list) of task to be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Calls <code>TaskManager</code> method to delete task and displays the task that was deleted.
     *
     * @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) throws IndexOutOfBoundsException, NumberFormatException {
        try {
            Task deletedTask = taskManager.deleteTask(index);
            ui.deleteTaskMessage(deletedTask);
        } catch (Exception e) {
            throw e;
        }
    }
}
