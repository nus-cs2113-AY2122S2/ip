package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represent the command to exit the app
 */
public class ExitCommand extends Command {
    /**
     * Exit the app
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
        storage.storeToFile(tasks.getTaskList());
        setIsExit(true);
    }
}
