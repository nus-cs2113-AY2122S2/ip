package duke.Commands;

/**
 * Unmark a task as undone by using its index from task list.
 */

public class UnmarkCommand extends Command{
    private int targetIndex;

    public UnmarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public void execute(){
        taskList.unmark(targetIndex);
    }
}
