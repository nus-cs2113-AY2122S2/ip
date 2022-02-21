package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class AddCommand extends Command {
    private ArrayList<String> pieces;

    public AddCommand(ArrayList<String> pieces) {
        this.pieces = pieces;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.addTask(pieces);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
