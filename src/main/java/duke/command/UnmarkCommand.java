package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;

/**
 * Represents the unmark command which is to be executed.
 */
public class UnmarkCommand extends Command {

    private String fullCommand;

    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Runs when there is an unmark command by identifying the task to be unmarked and proceeds to mark it as NOT done.
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
        int indexToUnmark = Parser.getIndex(fullCommand);
        Task taskToUnmark = tasks.getTask(indexToUnmark);
        taskToUnmark.setUndone();
        ui.showUnmarkCompleted(taskToUnmark);
        storage.saveAll(tasks);
    }

}
