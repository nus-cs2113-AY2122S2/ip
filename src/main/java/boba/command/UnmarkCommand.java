package boba.command;

import boba.data.Storage;
import boba.exception.BobaException;
import boba.response.Ui;
import boba.task.Task;
import boba.task.TaskList;

/**
 * Class for unmark command
 */
public class UnmarkCommand extends Command{

    private int index;

    public UnmarkCommand(String taskIndex) {
        index = Integer.parseInt(taskIndex) - 1;
    }

    /**
     * Executes instructions associated with Unmark
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.markTask(false, index);
            ui.printMarkSuccess(false, task);
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
