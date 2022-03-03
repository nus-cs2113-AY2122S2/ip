package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;
import marites.exception.MaritesException;
import marites.task.Task;

public class SetTaskStatusCommand extends Command {
    private final int taskIndexToSet;
    private final boolean newTaskStatus;

    public SetTaskStatusCommand(int i, boolean b) {
        super();
        taskIndexToSet = i;
        newTaskStatus = b;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws MaritesException {
        try {
            taskList.setTaskStatus(taskIndexToSet, newTaskStatus);
            Task setTask = taskList.getTaskByIndex(taskIndexToSet);
            ui.showSetTaskStatusMessage(setTask, newTaskStatus);
        } finally {
            storage.save(taskList);
        }
    }
}
