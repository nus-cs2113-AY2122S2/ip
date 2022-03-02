package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class ByeCommand extends Command {
    public boolean isExit() {
        return true;
    }

    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Ui.exit(storage,tasks);
    }
}
