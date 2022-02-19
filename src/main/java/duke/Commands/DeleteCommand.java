package duke.Commands;

import duke.exception.IndexOutOfRangeException;

public class DeleteCommand extends Command {
    protected int targetIndex;

    public DeleteCommand(int targetIndex){
        this.targetIndex = targetIndex;
    }

    public void execute() throws IndexOutOfRangeException {
        taskList.deleteTask(targetIndex);
    }
}
