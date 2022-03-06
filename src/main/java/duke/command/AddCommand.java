package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task task) {
        super();
        this.newTask = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
        ui.printAddMessage(newTask, tasks.getSize());
    }
}