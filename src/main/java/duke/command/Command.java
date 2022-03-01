package duke.command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public abstract class Command {

    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {

    }

    public boolean isExit(){
        return false;
    }
}
