package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;
import marites.exception.MaritesException;
import marites.task.Task;

public class DeleteCommand extends Command {
    private final int indexToDelete;
    public DeleteCommand(int i) {
        super();
        indexToDelete = i;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws MaritesException {
        try {
            Task deletedTask = taskList.getTaskByIndex(indexToDelete);
            taskList.deleteTask(indexToDelete);
            ui.showDeleteTaskMessage(deletedTask, taskList.getTaskCount());
        } finally {
            storage.save(taskList);
        }
    }
}
