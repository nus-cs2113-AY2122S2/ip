package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Command to terminate the program and exit out of Duke.
 */
public class ExitCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Bye. Hope to see you again soon!";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showToUser(MESSAGE_SUCCESS);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
