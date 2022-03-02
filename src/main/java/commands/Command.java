package commands;

import data.TaskManager;
import storage.FileManager;
import ui.Ui;

/**
 * Represents a general executable command.
 */
public abstract class Command {
    protected boolean isExit;

    public Command() {

    }

    /**
     * Execute the command.
     *
     * @param taskManager
     * @param fileManager
     * @param ui
     */
    public abstract void execute(TaskManager taskManager, FileManager fileManager, Ui ui);

    /**
     * Indicate whether the command is an exit command, which terminates the application.
     *
     * @return true if the command is exit command.
     */
    public boolean isExit() {
        return isExit;
    }
}
