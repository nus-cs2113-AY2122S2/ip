package bim.command;

import bim.BimException;
import bim.Storage;
import bim.Ui;
import bim.task.Task;
import bim.task.TaskList;

public class DeleteCommand extends Command {
    private static final String ERROR_INDEX = "Invalid index!";
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deletedTask = tasks.getTask(index);
            storage.deleteData(index);
            tasks.deleteTask(index);
            ui.printDeleteMessage(deletedTask, tasks.getSize());
        } catch (BimException exception) {
            ui.printErrorMessage(exception.getMessage());
        } catch (IndexOutOfBoundsException invalidIndex) {
            ui.printErrorMessage(ERROR_INDEX);
        }
    }
}
