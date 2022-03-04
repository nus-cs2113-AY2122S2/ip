package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public class DeleteCommand extends Command{

    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        taskManager.deleteTask(this.taskNumber);
        storage.writeFile(taskManager.toString());

    }
}
