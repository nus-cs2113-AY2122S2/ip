package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command to mark task as done or not
 */
public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isDone;

    /**
     * Constructs the command
     * 
     * @param index
     *            index of the task to be marked
     * @param isDone
     *            status of the task to mark
     */
    public MarkCommand(int index, boolean isDone) {
        this.taskIndex = index;
        this.isDone = isDone;
    }

    /**
     * Executes the command to mark the task
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(taskIndex);
        tasks.markTask(task, isDone);
        ui.printMarkMessage(isDone);
        ui.printTask(task);
    }
}
