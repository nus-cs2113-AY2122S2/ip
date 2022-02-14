package duke.Command;

import duke.TaskList;
import duke.UI;
import duke.save;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int index) {
        this.taskIndex = index;
    }

    public void execute(TaskList tasks, UI ui, save save) {
        Task task = tasks.getTask(taskIndex);
        tasks.removeTask(task);
        ui.printRemoveMessage(task, tasks.getSize());
    }
}