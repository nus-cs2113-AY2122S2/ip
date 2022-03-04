import java.util.ArrayList;

public class CompleteTaskCommand extends Command {
    private boolean hasCompleteTask;
    private int taskIndex;

    public CompleteTaskCommand(boolean hasCompleteTask, int taskIndex) {
        this.hasCompleteTask = hasCompleteTask;
        this.taskIndex = taskIndex;
    }

    /**
     * Execute complete task function to mark done task identified by the user.
     * Updates details in the file. Prints remaining number of wish task.
     *
     * @param taskList    contains all wish task.
     * @param uiManager   assist in printing exit message.
     * @param fileManager manages the file.
     * @throws DukeWrongCommandException when task number does not exist in the list.
     */
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
