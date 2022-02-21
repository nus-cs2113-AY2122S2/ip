package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Prints a goodbye message and returns.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
