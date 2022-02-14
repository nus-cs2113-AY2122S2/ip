package duke.Command;

import duke.TaskList;
import duke.UI;
import duke.save;
import duke.task.Task;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task task) {
        super();
        this.newTask = task;
    }

    public void execute(TaskList tasks, UI ui, save save) {
        tasks.addTask(newTask);
        ui.printAddMessage(newTask, tasks.getSize());
    }
}