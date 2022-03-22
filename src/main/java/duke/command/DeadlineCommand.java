package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeadlineCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:";
    private final Task task;

    public DeadlineCommand(Task task) {
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
