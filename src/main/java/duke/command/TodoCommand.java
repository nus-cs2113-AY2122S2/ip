package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

public class TodoCommand extends Command {

    private static final String TYPE_OF_TASK = "todo";

    private String fullCommand;

    public TodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException, IOException {
        String description = getDescription();
        ToDo toDo = new ToDo(description, TYPE_OF_TASK);
        tasks.addTask(toDo);
        ui.showAddDone(toDo, tasks.getSize());
        storage.save(toDo);
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
        String description = getDescription();
        listOfTasks.add(new ToDo(description, TYPE_OF_TASK));
    }

    private String getDescription() throws AdditionalException {
        int lengthOfTypeOfTask = TYPE_OF_TASK.length();
        int lengthOfRequest = fullCommand.length();
        String description = fullCommand.substring(lengthOfTypeOfTask, lengthOfRequest);
        String trimmedDescription = description.trim();
        if (trimmedDescription.length() < 1) {
            throw new AdditionalException("OOPS!!! The description cannot be empty.");
        }
        return trimmedDescription;
    }

}
