package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command to unmark a Task found in the current TaskList
 * as not completed.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_SUCCESS = "OK, I've marked this task as not done yet:";
    public static final String EXCEPTION_WRONG_INPUT = "Please enter an index within range.";
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskIndex > tasks.getSize() - 1) {
            throw new DukeException(EXCEPTION_WRONG_INPUT);
        }

        Task task = tasks.getTask(this.taskIndex).unmarkTask();
        storage.writeTasksToStorage(tasks);

        ui.showToUser(
                MESSAGE_SUCCESS,
                task.toString());
    }
}
