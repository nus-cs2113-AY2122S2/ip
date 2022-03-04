package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public class AddCommand extends Command{

    private String commandType;
    private String taskName;
    private String addInfo;


    public AddCommand(String commandType, String taskName, String addInfo) {
        this.taskName = taskName;
        this.commandType = commandType;
        this.addInfo = addInfo;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        taskManager.addTask(this.commandType, this.taskName, this.addInfo);
        storage.writeFile(taskManager.toString());
    }
}
