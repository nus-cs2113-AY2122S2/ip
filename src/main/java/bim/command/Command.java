package bim.command;

import bim.Storage;
import bim.Ui;
import bim.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
