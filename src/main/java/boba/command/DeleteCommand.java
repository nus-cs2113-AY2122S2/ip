package boba.command;

import boba.data.Storage;
import boba.exception.BobaException;
import boba.response.Ui;
import boba.task.Task;
import boba.task.TaskList;

public class DeleteCommand extends Command{

    private int index;

    public DeleteCommand(String taskIndex) {
        index = Integer.parseInt(taskIndex) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.removeTask(index);
            ui.printRemoveSuccess(removedTask, tasks.size());
            storage.save(tasks);
        } catch (BobaException e) {
            ui.printOutOfBounds();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
