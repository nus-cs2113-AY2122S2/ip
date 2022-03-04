package boba.command;

import boba.data.Storage;
import boba.response.Ui;
import boba.task.TaskList;

/**
 * Class for when invalid command is inputted
 */
public class NoneCommand extends Command{

    /**
     * Print out invalid statement
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printInvalidCommand("Type <help> for all available commands");
    }

    /**
     * @return Not ExitCommand return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
