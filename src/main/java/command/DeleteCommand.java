package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Deadlines;
import task.Task;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.delete(this.taskIndex);
        return ui.generateResponse("Noted. I've removed this task:\n" + taskList.getTask(taskIndex) + "\n" +
                    "Now you have " + (taskList.amountOfTasks() - 1) +" tasks in the list.\n");
    }
}
