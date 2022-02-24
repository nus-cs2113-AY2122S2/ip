/**
 * This package is adapted from https://github.com/se-edu/addressbook-level2
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Parent class for all command classes.
 * This class should not be instantiated.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param ui user interface to execute to.
     * @param tasks task list to execute on.
     * @param storage storage (save file) to execute on.
     */
    public abstract void execute(Ui ui, TaskList tasks, Storage storage);

    /**
     * Determines whether the program should exit after running this command.
     * @return true if the program should exit.
     */
    public abstract boolean isExit();
}
