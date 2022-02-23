package bim.command;

import bim.Storage;
import bim.Ui;
import bim.task.TaskList;

/**
 * Any command that was not properly understood by the parser.
 */
public class IncorrectCommand extends Command {
    private String description;

    public IncorrectCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printErrorMessage(description);
    }
}
