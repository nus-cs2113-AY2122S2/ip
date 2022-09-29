package Commands;

import Misc.DukeException;
import Misc.Storage;
import Misc.Ui;
import Tasks.TaskList;

import java.util.ArrayList;

public class MarkCommand extends Command {
    public MarkCommand(ArrayList<String> elements) {
        super(elements);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index;

        try{
            index = Integer.parseInt(elements.get(1)) - 1;
            if (index < 0 | index >= tasks.getTasks().size()) {
                throw new DukeException(6);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(6);
        }

        switch (elements.get(0)) {
            case "mark":
                tasks.getTasks().get(index).setDone();
                ui.showMarkTaskNotification(tasks.getTasks().get(index).toString());
                break;
            case "unmark":
                tasks.getTasks().get(index).setNotDone();
                ui.showUnmarkTaskNotification(tasks.getTasks().get(index).toString());
                break;
        }
        storage.saveTasks(tasks.getTasks());
    }
}
