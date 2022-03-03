package boba.command;

import boba.data.Storage;
import boba.response.Ui;
import boba.task.TaskList;

/**
 * Abstract Class for the valid Commands allowed for Boba chat-bot
 */
public abstract class Command {

    /**
     * Executes instructions base on command type
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Boolean to see if we exit from Boba
     * @return returns true only for ExitCommand
     */
    public abstract boolean isExit();

}
