package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.InvalidArgumentException;

public abstract class Command {
    protected boolean isExit;

    /**
     * Classes should override and implement this as needed, namely those that requires additional user arguments
     *
     * @throws InvalidArgumentException any exception that may occur while parsing
     */
    protected void assertArguments() throws InvalidArgumentException {

    }

    /**
     * A command must execute an action.
     * @param taskList the taskList to act on
     * @param ui the provided Ui to output on
     * @param storage the provided filename to update data to
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }
}
