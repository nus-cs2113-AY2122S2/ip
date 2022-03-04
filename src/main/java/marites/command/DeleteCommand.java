package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;
import marites.exception.MaritesException;
import marites.task.Task;

/**
 * A class that represents delete commands.
 */
public class DeleteCommand extends Command {
    private final int indexToDelete;
    public DeleteCommand(int i) {
        super();
        indexToDelete = i;
    }

    /**
     * Executes the DeleteCommand.
     * @param storage A Storage instance for handling storage
     * @param ui A Ui instance for providing output
     * @param taskList A TaskList instance for managing tasks
     * @throws MaritesException if an error occurs during execution.
     */
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
