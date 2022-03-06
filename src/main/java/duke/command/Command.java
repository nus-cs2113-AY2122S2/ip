package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
