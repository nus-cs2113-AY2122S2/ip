package duke.Commands;

import duke.exception.IllegalFormatException;
import duke.exception.IndexOutOfRangeException;

/**
 * Marks a task as done by using its index from the task list.
 */
public class MarkCommand extends Command{
    private int targetIndex;
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public void execute() throws IndexOutOfRangeException {
        if(targetIndex <=0 || targetIndex > taskList.getNumberOfTasks()){
            throw new IndexOutOfRangeException();
        }
        taskList.mark(targetIndex);
    }
}
