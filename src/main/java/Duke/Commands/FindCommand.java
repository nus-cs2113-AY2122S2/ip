package Duke.Commands;

import Duke.DukeException;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class FindCommand extends Command {
    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Ui.listItems(tasks.findItem(description));
    }
}
