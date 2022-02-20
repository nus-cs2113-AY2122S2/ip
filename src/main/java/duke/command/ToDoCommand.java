package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the todo command which is to be executed.
 */
public class ToDoCommand extends Command {

    private static final String TYPE_OF_TASK = "todo";

    private String fullCommand;

    public ToDoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * This is the execute method that runs when there is a todo command.
     * The method will add the task to the list of tasks in the TaskList object.
     * It will then print the confirmation for adding the task and updates the file.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the confirmation message.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws IOException If there is an error saving tasks to the file.
     * @see IOException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException, IOException {
        String description = getDescription();
        ToDo toDo = new ToDo(description, TYPE_OF_TASK);
        tasks.addTask(toDo);
        ui.showAddDone(toDo, tasks.getSize());
        storage.save(toDo);
    }

    /**
     * This is the isBye method that returns whether the command is "bye".
     *
     * @return False because the command is "todo".
     */
    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * This is the executeFromFile method that takes in a list of tasks and adds a new ToDo task to the list of tasks.
     *
     * @param listOfTasks This is the list of tasks that the new task is to be added to.
     * @throws AdditionalException If there is no description provided in the fullCommand.
     * @see AdditionalException
     */
    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
        String description = getDescription();
        listOfTasks.add(new ToDo(description, TYPE_OF_TASK));
    }

    /**
     * This is the getDescription method that returns the description of the task from the fullCommand.
     *
     * @return The description of the task to be added.
     * @throws AdditionalException If there is no description provided in the fullCommand.
     * @see AdditionalException
     */
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
