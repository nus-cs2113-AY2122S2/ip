package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;
import marites.exception.MaritesException;

/**
 * Abstract base class for commands.
 */
abstract public class Command {
    /**
     * Returns whether this Command is an ExitCommand.
     * @return Exit or not
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the given command.
     * @param storage A Storage instance for handling storage
     * @param ui A Ui instance for providing output
     * @param taskList A TaskList instance for managing tasks
     * @throws MaritesException if an error occurs during execution.
     */
    abstract public void execute(Storage storage, Ui ui, TaskList taskList) throws MaritesException;
}
