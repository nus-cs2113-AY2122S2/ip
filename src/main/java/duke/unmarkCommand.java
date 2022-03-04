package duke;

import duke.task.Task;

public class unmarkCommand extends Command {
    private final int taskIndex;

    public unmarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex).unmarkTask();
        storage.writeTasksToStorage(tasks);

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
}
