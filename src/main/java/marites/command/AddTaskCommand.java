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
        taskList.addTask(taskToAdd);
        ui.showAddTaskMessage(taskToAdd, taskList.getTaskCount());
        storage.save(taskList);
    }
}
