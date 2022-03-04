package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Command to tell the user that his/her input is invalid,
 * and they should type a command that is recognized.
 */
public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        // Throw exception if unknown command inputted
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
