package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command to add a new task
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * Construct command
     * 
     * @param task
     *            Task to add
     */
    public AddCommand(Task task) {
        super();
        this.newTask = task;
    }

    /**
     * Execute the add command
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
        ui.printAddMessage(newTask, tasks.getSize());
    }
}
