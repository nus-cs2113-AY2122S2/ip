package commands;

import data.TaskManager;
import storage.FileManager;
import ui.Ui;

public abstract class Command {

    public Command() {

    }

    public abstract void execute(TaskManager taskManager, FileManager fileManager, Ui ui);
}
