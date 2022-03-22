package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command to mark a Task found in the TaskList as completed.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:";
    public static final String EXCEPTION_WRONG_INPUT = "Please enter an index within range.";
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskIndex > tasks.getSize() - 1) {
            throw new DukeException(EXCEPTION_WRONG_INPUT);
        }

        Task task = tasks.getTask(this.taskIndex).markTask();
        storage.writeTasksToStorage(tasks);

        ui.showToUser(
                MESSAGE_SUCCESS,
                task.toString());
    }
}
