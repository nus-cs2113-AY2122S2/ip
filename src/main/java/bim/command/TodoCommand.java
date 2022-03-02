package bim.command;

import bim.BimException;
import bim.Storage;
import bim.Ui;
import bim.task.TaskList;
import bim.task.ToDo;

/**
 * Adds a todo task to the task list when executed.
 */
public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Writes the new task to the data file. Only if the writing is successful, add the new task
     * to the task list and print the newly added task.
     *
     * @param tasks The task list to add the new todo into
     * @param ui Ui object to print messages
     * @param storage Storage object for saving to disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo newTodo = new ToDo(description);
        try {
            storage.writeData(newTodo);
            tasks.addTask(newTodo);
            ui.printAddTaskMessage(newTodo, tasks.getSize());
        } catch (BimException exception) {
            ui.printErrorMessage(exception.getMessage());
        }
    }
}
