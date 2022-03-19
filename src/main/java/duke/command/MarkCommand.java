package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private final String taskToMark;

    public MarkCommand(String taskToMark) {
        this.taskToMark = taskToMark;
    }

    /**
     * @param ui user interface to execute to.
     * @param tasks task list to execute on.
     * @param storage storage (save file) to execute on.
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.doTask(taskToMark);
    }

    /**
     * Determines if the program should terminate after executing this command.
     * @return false as MarkCommand should not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
