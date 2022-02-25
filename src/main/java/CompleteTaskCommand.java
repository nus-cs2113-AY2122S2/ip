import java.util.ArrayList;

public class CompleteTaskCommand extends Command {
    private boolean hasCompleteTask;
    private int taskIndex;

    public CompleteTaskCommand(boolean hasCompleteTask, int taskIndex) {
        this.hasCompleteTask = hasCompleteTask;
        this.taskIndex = taskIndex;
    }

    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) throws DukeWrongCommandException {
        if (taskIndex < 0) {
            throw new DukeWrongCommandException("Invalid task index! Task should be at least 1");
        }
        if (taskIndex >= taskList.size()) {
            throw new DukeWrongCommandException("Invalid task index! Task number given exceeded Total Task in List");
        }
        taskList.get(taskIndex).setCompleted(hasCompleteTask);
        uiManager.printMessage((taskList.get(taskIndex)).toString());
        updateToFile("wishlist.txt", taskList, fileManager);
    }
}
