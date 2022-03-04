package boba.command;

import boba.data.Storage;
import boba.exception.BobaException;
import boba.response.Ui;
import boba.task.Task;
import boba.task.TaskList;

/**
 * Class for delete command
 */
public class DeleteCommand extends Command{

    /** index of task to be deleted */
    private int index;

    /**
     * Constructor for DeleteCommand
     * @param taskIndex
     */
    public DeleteCommand(String taskIndex) {
        index = Integer.parseInt(taskIndex) - 1;
    }

    /**
     * Delete designated task from taskList
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.removeTask(index);
            ui.printRemoveSuccess(removedTask, tasks.size());
            storage.save(tasks);
        } catch (BobaException e) {
            ui.printOutOfBounds();
        }
    }

    /**
     * @return Not ExitCommand return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
