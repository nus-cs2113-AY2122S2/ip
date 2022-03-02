package Duke.Commands;

import Duke.*;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

/**
 * Represents the command to list items.
 */
public class ListItemCommand  extends Command {

    /**
     * Indicates whether the program should exit.
     *
     * @return Indication on whether the program should exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks Tasklist containing of  the tasks.
     * @param ui    User interface of Duke.
     * @param storage   Storage of Duke.
     */
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Ui.listItems(tasks);
    }
}
