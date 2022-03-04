package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;
import marites.task.Task;

/**
 * A class that represents add commands.
 */
public class AddTaskCommand extends Command {
    private final Task taskToAdd;
    public AddTaskCommand(Task newTask) {
        super();
        this.taskToAdd = newTask;
    }
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showAddTaskMessage(taskToAdd, taskList.getTaskCount());
        taskList.addTask(taskToAdd);
        storage.save(taskList);
    }
}
