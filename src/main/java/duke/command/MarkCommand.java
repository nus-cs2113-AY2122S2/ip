package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command to mark a Task found in the TaskList as completed.
 */
public class MarkCommand extends Command {
    private final int taskIndex;
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:";

    public MarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex).markTask();
        storage.writeTasksToStorage(tasks);

        ui.showToUser(
                MESSAGE_SUCCESS,
                task.toString());
    }
}
