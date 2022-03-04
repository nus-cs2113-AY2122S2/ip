package boba.command;

import boba.data.Storage;
import boba.exception.BobaException;
import boba.response.Ui;
import boba.task.Task;
import boba.task.TaskList;

/**
 * class for mark commadn
 */
public class MarkCommand extends Command{

    /** index of task to be marked */
    private int index;

    /**
     * Constructor for MarkCommand
     * @param taskIndex
     */
    public MarkCommand(String taskIndex) {
        index = Integer.parseInt(taskIndex) - 1;
    }

    /**
     * Executes instructions base on command type
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.markTask(true, index);
            ui.printMarkSuccess(true, task);
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
