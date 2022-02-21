package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class DeleteCommand extends Command {
    private String taskToDelete;

    public DeleteCommand(String taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.deleteTask(taskToDelete);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
