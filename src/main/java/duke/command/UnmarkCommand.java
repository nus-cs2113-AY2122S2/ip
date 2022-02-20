package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the unmark command which is to be executed.
 */
public class UnmarkCommand extends Command {

    private String fullCommand;

    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * This is the execute method that runs when there is an unmark command.
     * The method will identify the task to be unmarked and proceeds to mark it as NOT done.
     * It will then print the confirmation for unmarking the task and updates the file.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the confirmation message.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws IOException If there is an error saving tasks to the file.
     * @see IOException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, AdditionalException {
        int indexToUnmark = getIndex(fullCommand);
        Task taskToUnmark = tasks.getTask(indexToUnmark);
        taskToUnmark.markAsUndone();
        ui.showUnmarkCompleted(taskToUnmark);
        storage.saveAll(tasks);
    }

    /**
     * This is the isBye method that returns whether the command is "bye".
     *
     * @return False because the command is "unmark".
     */
    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {

    }

}
