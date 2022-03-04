package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;

/**
 * A class for representing list commands.
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand.
     * @param storage A Storage instance for handling storage
     * @param ui A Ui instance for providing output
     * @param taskList A TaskList instance for managing tasks
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showTaskList(taskList);
    }
}
