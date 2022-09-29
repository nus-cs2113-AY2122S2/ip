package Commands;

import Misc.DukeException;
import Misc.Storage;
import Misc.Ui;
import Tasks.TaskList;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}
