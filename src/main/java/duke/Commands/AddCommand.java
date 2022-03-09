package duke.Commands;

import duke.tasks.Task;

/**
 * Adds a task to task list.
 */
public class AddCommand extends Command{

    private Task toAdd;

    public AddCommand(Task toAdd){
        this.toAdd = toAdd;
    }

    @Override
    public void execute() {
        taskList.addTask(toAdd);
    }
}
