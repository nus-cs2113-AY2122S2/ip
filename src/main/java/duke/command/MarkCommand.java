package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isDone;


    public MarkCommand(int index, boolean isDone) {
        this.taskIndex = index;
        this.isDone = isDone;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(taskIndex);
        tasks.markTask(task, isDone);
        ui.printMarkMessage(isDone);
        ui.printTask(task);
    }
}