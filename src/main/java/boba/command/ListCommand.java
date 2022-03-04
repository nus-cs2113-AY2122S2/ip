package boba.command;

import boba.data.Storage;
import boba.response.Ui;
import boba.task.TaskList;

/**
 * Class for list command
 */
public class ListCommand extends Command{

    /**
     * List out all tasks in taskList
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printAllTasks(tasks);
    }

    /**
     * @return Not ExitCommand return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
