package duke.Command;

import duke.TaskList;
import duke.UI;
import duke.save;
import duke.task.Task;
public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isDone;
    public MarkCommand(int index, boolean isDone) {
        this.taskIndex = index;
        this.isDone = isDone;
    }
    
    public void execute(TaskList tasks, UI ui, save save) {
        Task task = tasks.getTask(taskIndex);
        tasks.doneTask(task, isDone);
        ui.printMarkMessage(isDone);
        ui.printTask(task);
    }
}