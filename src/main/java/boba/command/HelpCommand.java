package boba.command;

import boba.data.Storage;
import boba.response.Ui;
import boba.task.TaskList;

public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHelpOptions();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
