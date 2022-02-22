package bim.command;

import bim.Storage;
import bim.Ui;
import bim.task.TaskList;

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
