package Commands;

import Misc.DukeException;
import Misc.Storage;
import Misc.Ui;
import Tasks.*;

import java.util.ArrayList;

public class Command {
    ArrayList<String> elements;
    public Command(ArrayList<String> elements) {
        this.elements = elements;
    }

    public Command() {
    }

    /**
     * Executes the command.
     * @param tasks Tasks list.
     * @param ui UI object.
     * @param storage Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    /**
     * Checks if the program should be terminated.
     * @return Status.
     */
    public boolean isExit() {
        return false;
    }
}
