/**
 * This package is adapted from https://github.com/se-edu/addressbook-level2
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public abstract void execute(Ui ui, TaskList tasks, Storage storage);
    public abstract boolean isExit();
}
