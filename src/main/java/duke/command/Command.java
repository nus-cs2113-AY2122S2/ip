package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the command which is to be executed.
 */
public abstract class Command {

    /**
     * Returns false if the command is not "bye".
     * By default, the method returns false and is only overridden by the ByeCommand.
     *
     * @return false by default.
     */
    public boolean isBye() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException, IOException {
    }

    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
    }

}
