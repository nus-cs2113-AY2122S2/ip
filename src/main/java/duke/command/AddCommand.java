package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command to add a Task to the TaskList.
 * Different types of Task can be added based on the user input.
 */
public class AddCommand extends Command {
    private final Task task;
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:";

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        storage.writeTasksToStorage(tasks);

        ui.showToUser(
                MESSAGE_SUCCESS,
                String.format("  %s", this.task),
                tasks.getRemainingTasksStr());
    }
}
