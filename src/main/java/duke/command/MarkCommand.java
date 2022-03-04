package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command to mark a Task found in the TaskList as completed.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex).markTask();
        storage.writeTasksToStorage(tasks);

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }
}
