package duke.Commands;

public class UnmarkCommand extends Command{
    private int targetIndex;

    public UnmarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public void execute(){
        taskList.unmark(targetIndex);
    }
}
