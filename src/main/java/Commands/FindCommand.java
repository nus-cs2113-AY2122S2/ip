package Commands;

import Misc.DukeException;
import Misc.Storage;
import Misc.Ui;
import Tasks.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(ArrayList<String> elements) {
        super(elements);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showFindTaskNotification(tasks.getTasks(), elements.get(1));
    }
}
