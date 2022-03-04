package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.removeTask(this.taskIndex);
        storage.writeTasksToStorage(tasks);

        System.out.println("Noted. I've removed this task");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }
}
