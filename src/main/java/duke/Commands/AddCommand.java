package duke.Commands;

import duke.tasks.Task;

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
