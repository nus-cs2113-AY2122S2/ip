package bim.command;

import bim.Storage;
import bim.Ui;
import bim.task.TaskList;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks);
    }
}
