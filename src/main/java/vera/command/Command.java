package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
