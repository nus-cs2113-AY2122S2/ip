package Duke.Commands;

import Duke.*;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class ListItemCommand  extends Command {
    public boolean isExit() {
        return false;
    }

    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Ui.listItems(tasks);
    }
}
