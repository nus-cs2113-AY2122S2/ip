package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int index) {
        this.taskIndex = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(taskIndex);
        tasks.removeTask(task);
        ui.printRemoveMessage(task, tasks.getSize());
    }
}