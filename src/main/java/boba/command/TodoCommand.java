package boba.command;

import boba.data.Storage;
import boba.exception.BobaException;
import boba.response.Ui;
import boba.task.TaskList;
import boba.task.Todo;

/**
 * Class for todo command
 */
public class TodoCommand extends Command{

    /** Todo item to be added to taskList */
    private Todo todo;

    /**
     * Constructor for TodoCommand
     * @param description
     */
    public TodoCommand(String description) {
        todo = new Todo(description);
    }

    /**
     * Add a todo item to taskList
     * @param tasks List of all tasks
     * @param ui UI of Boba
     * @param storage Save file of list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(todo);
            ui.printAddSuccess(todo, tasks.size());
            storage.save(tasks);
        } catch (BobaException e) {
            ui.printLimitError();
        }
    }

    /**
     * @return Not ExitCommand return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
