package boba.command;

import boba.data.Storage;
import boba.exception.BobaException;
import boba.response.Ui;
import boba.task.TaskList;
import boba.task.Todo;

public class TodoCommand extends Command{

    private Todo todo;

    public TodoCommand(String description) {
        todo = new Todo(description);
    }

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

    @Override
    public boolean isExit() {
        return false;
    }

}
