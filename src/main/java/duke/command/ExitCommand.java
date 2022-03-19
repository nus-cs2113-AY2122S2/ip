package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Prints a goodbye message and returns.
 */
public class ExitCommand extends Command {
    /**
     * @param ui user interface to execute to.
     * @param tasks task list to execute on.
     * @param storage storage (save file) to execute on.
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    /**
     * Determines if the program should terminate after executing this command.
     * @return true as ExitCommand should terminate the program.
     */
    public boolean isExit() {
        return true;
    }
}
