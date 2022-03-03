package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showTaskList(taskList);
    }
}
