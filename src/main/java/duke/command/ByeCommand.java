package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the bye command which is to be executed.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    /**
     * This is the isBye method that returns whether the command is "bye".
     *
     * @return True because the command is "bye".
     */
    @Override
    public boolean isBye() {
        return true;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) {
    }

}
