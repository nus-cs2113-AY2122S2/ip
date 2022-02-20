package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException {
        ArrayList<Task> listOfTasks = tasks.getList();
        ui.showList(listOfTasks);
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) {
    }
}
