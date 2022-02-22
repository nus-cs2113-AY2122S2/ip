package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.parser.Parser;
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
        String description = Parser.getDescription(TYPE_OF_TASK, fullCommand);
        if (description.length() < 1) {
            throw new AdditionalException("What are you going to do? LOL");
        }
        ToDo toDo = new ToDo(description, TYPE_OF_TASK);
        tasks.addTask(toDo);
        ui.showAddDone(toDo, tasks.getSize());
        storage.save(toDo);
    }

    /**
     * This is the executeFromFile method that takes in a list of tasks and adds a new task to the list of tasks.
     *
     * @param listOfTasks This is the list of tasks that the new task is to be added to.
     * @throws AdditionalException If the file has been edited and hence the fullCommand is incorrect.
     * @see AdditionalException
     */
    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
        String description = Parser.getDescription(TYPE_OF_TASK, fullCommand);
        if (description.length() < 1) {
            throw new AdditionalException("Did you accidentally edit the file?");
        }
        listOfTasks.add(new ToDo(description, TYPE_OF_TASK));
    }

}
