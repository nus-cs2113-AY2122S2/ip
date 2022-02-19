package duke.Commands;

public class MarkCommand extends Command{
    private int targetIndex;
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public void execute(){
        taskList.mark(targetIndex);
    }
}
