package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public Command() {
    }

    /**
     * All types of Command will be executable and invoked later on
     *
     * @param tasks TaskList containing all the tasks that have been saved
     * @param ui User interface for reading user inputs or printing outputs
     * @param storage Current Storage object containing the filepath to
     *                write data to after execution
     * @throws DukeException If there are errors specific to Duke that are raised when
     * executing the commands
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    // Default returns false unless specified
    public boolean isExit() {
        return false;
    }

}
