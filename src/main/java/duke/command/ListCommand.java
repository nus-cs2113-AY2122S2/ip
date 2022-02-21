package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.listTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
