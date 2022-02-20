package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;

import java.util.ArrayList;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public boolean isBye() {
        return true;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) {
    }

}
