package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

/**
 * Adds a task.
 * Supported tasks:
 * Todo, Deadline, Event
 */
public class AddCommand extends Command {
    private final ArrayList<String> pieces;

    public AddCommand(ArrayList<String> pieces) {
        this.pieces = pieces;
    }

    /**
     * @param ui user interface to execute to.
     * @param tasks task list to execute on.
     * @param storage storage (save file) to execute on.
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.addTask(pieces);
    }

    /**
     * Determines if the program should terminate after executing this command.
     * @return false as AddCommand should not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
