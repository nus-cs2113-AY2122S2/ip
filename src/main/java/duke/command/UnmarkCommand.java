package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private final String taskToUnmark;

    public UnmarkCommand(String taskToUnmark) {
        this.taskToUnmark = taskToUnmark;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.undoTask(taskToUnmark);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
