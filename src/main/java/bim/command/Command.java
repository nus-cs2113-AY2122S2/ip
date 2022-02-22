package bim.command;

import bim.BimException;
import bim.Storage;
import bim.Ui;
import bim.task.Task;
import bim.task.TaskList;

public abstract class Command {

    public Command() {   }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
