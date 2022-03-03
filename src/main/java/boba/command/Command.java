package boba.command;

import boba.data.Storage;
import boba.response.Ui;
import boba.task.TaskList;

/**
 * Enum for the valid Commands allowed for Boba chat-bot
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();

}
