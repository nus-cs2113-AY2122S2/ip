package duke.command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public abstract class Command {

    /**
     * Executes the command
     *
     * @param taskManager TaskManager instance in charge of calling the correct method
     * @param ui Ui instance in charge to print
     * @param storage Storage instance for writing files
     * @throws DukeException
     */
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {

    }

    public boolean isExit(){
        return false;
    }
}
