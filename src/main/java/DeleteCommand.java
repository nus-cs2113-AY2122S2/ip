import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) throws DukeWrongCommandException {
        try {
            uiManager.printMessage("oh no, this task has been deleted: " + System.lineSeparator() + taskList.get(taskIndex));
            taskList.remove(taskIndex);
            uiManager.printNumberOfWish(taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeWrongCommandException("Invalid task number to be removed!");
        }
    }
}
