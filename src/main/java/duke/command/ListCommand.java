package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the list command which is to be executed.
 */
public class ListCommand extends Command {

    /**
     * Runs when there is a list command by printing the list of tasks.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the confirmation message.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws AdditionalException If there are no tasks.
     * @see AdditionalException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException {
        ArrayList<Task> listOfTasks = tasks.getList();
        ui.showList(listOfTasks);
    }

}
