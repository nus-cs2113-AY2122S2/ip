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

    /**
     * @param ui user interface to execute to.
     * @param tasks task list to execute on.
     * @param storage storage (save file) to execute on.
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.deleteTask(taskToDelete);
    }

    /**
     * Determines if the program should terminate after executing this command.
     * @return false as DeleteCommand should not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
