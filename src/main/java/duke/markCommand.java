package duke;

import duke.task.Task;

public class markCommand extends Command {
    private final int taskIndex;

    public markCommand(int taskIndex) {
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
