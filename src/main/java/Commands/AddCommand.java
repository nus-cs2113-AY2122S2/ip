package Commands;

import Misc.DukeException;
import Misc.Storage;
import Misc.Ui;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.TaskList;
import Tasks.ToDo;

import java.util.ArrayList;

public class AddCommand extends Command {
    public AddCommand(ArrayList<String> elements) {
        super(elements);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (elements.get(0)) {
            case "todo":
                if (elements.size() < 2) {
                    throw new DukeException(1);
                }
                tasks.addTask(new ToDo(elements.get(1)));
                break;
            case "deadline":
                if (elements.size() < 3) {
                    throw new DukeException(2);
                }
                tasks.addTask(new Deadline(elements.get(1), elements.get(2)));
                break;
            case "event":
                if (elements.size() < 3) {
                    throw new DukeException(3);
                }
                tasks.addTask(new Event(elements.get(1), elements.get(2)));
                break;
        }
        ui.showAddTaskNotification(tasks.getTasks());
        storage.saveTasks(tasks.getTasks());
    }
}
