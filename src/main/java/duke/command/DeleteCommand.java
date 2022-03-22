package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command to delete a Task from the TaskList
 */
public class DeleteCommand extends Command {
    private final int taskIndex;
    public static final String MESSAGE_SUCCESS = "Noted. I've removed this task";

    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.removeTask(this.taskIndex);
        storage.writeTasksToStorage(tasks);

        ui.showToUser(
                MESSAGE_SUCCESS,
                task.toString(),
                tasks.getRemainingTasksStr());
    }
}
