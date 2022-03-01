package duke.command;

import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        Ui.printWithDivider(taskManager.toString());
    }
}
