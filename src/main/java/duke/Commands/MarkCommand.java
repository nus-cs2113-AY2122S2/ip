package duke.Commands;

/**
 * Mark a task as done by using its index from the task list.
 */
public class MarkCommand extends Command{
    private int targetIndex;
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public void execute(){
        taskList.mark(targetIndex);
    }
}
