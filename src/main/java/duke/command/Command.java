package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException, IOException;
    public abstract boolean isBye();
    public abstract void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException;

}
