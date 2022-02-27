package commands;

import data.TaskManager;
import storage.FileManager;
import ui.Ui;

public abstract class Command {
    protected boolean isExit;

    public Command() {

    }

    public abstract void execute(TaskManager taskManager, FileManager fileManager, Ui ui);

    public boolean isExit() {
        return isExit;
    }
}
