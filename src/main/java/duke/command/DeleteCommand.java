package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represent the command to delete a task
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Construct the command
     * 
     * @param index
     *            index of task to be removed
     */
    public DeleteCommand(int index) {
        this.taskIndex = index;
    }

    /**
     * Removes the task from the task list
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(taskIndex);
        tasks.removeTask(task);
        ui.printRemoveMessage(task, tasks.getSize());
    }
}
