package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;
import marites.exception.MaritesException;
import marites.task.Task;

/**
 * A class for representing task set status commands.
 */
public class SetTaskStatusCommand extends Command {
    private final int taskIndexToSet;
    private final boolean newTaskStatus;

    public SetTaskStatusCommand(int i, boolean b) {
        super();
        taskIndexToSet = i;
        newTaskStatus = b;
    }

    /**
     * Executes the SetTaskStatusCommand.
     * @param storage A Storage instance for handling storage
     * @param ui A Ui instance for providing output
     * @param taskList A TaskList instance for managing tasks
     * @throws MaritesException if an error occurs.
     */
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
