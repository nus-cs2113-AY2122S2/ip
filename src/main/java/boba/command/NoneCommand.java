package boba.command;

import boba.data.Storage;
import boba.response.Ui;
import boba.task.TaskList;

public class NoneCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printInvalidCommand("Type <help> for all available commands");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
