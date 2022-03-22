package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command to unmark a Task found in the current TaskList
 * as not completed.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;
    public static final String MESSAGE_SUCCESS = "OK, I've marked this task as not done yet:";

    public UnmarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex).unmarkTask();
        storage.writeTasksToStorage(tasks);

        ui.showToUser(
                MESSAGE_SUCCESS,
                task.toString());
    }
}
