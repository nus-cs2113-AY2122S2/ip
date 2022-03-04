package boba.command;

import boba.data.Storage;
import boba.response.Ui;
import boba.task.TaskList;

/**
 * Class for bye command
 */
public class ExitCommand extends Command {

    /**
     * Saves list and says goodbye
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.sayGoodbye();
    }

    /**
     * @return ExitCommand return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
