package boba.command;

import boba.data.Storage;
import boba.response.Ui;
import boba.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
