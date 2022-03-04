import java.util.ArrayList;

/**
 * Identify wish task user want to delete from the list of wish task.
 * This action call an updated to also erase wish task from the file.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Execute delete function to remove task identified by the user.
     * Updates details in the file. Prints remaining number of wish task
     * in the list as well as the wish that has been removed.
     *
     * @param taskList    contains all wish task.
     * @param uiManager   assist in printing exit message.
     * @param fileManager manages the file.
     * @throws DukeWrongCommandException when task number does not exist in the list.
     */
    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) throws DukeWrongCommandException {
        try {
            uiManager.printMessage("oh no, this task has been deleted: " + System.lineSeparator() + taskList.get(taskIndex));
            taskList.remove(taskIndex);
            uiManager.printNumberOfWish(taskList.size());
        } catch (IndexOutOfBoundsException error) {
            throw new DukeWrongCommandException("Invalid task number to be removed!");
        }
        updateToFile("wishlist.txt", taskList, fileManager);
    }
}
