package bim.command;

import bim.Storage;
import bim.Ui;
import bim.task.TaskList;

/**
 * Prints the exit message.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
    }
}
