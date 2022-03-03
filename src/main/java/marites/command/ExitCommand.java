package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showExitMessage();
    }
}
