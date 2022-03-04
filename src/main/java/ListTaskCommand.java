import java.util.ArrayList;

/**
 * Represent the function in which user will be able to get the
 * full list of wish list task being stored in tasklist.
 */
public class ListTaskCommand extends Command {

    /**
     * Execute printing of full wish tasklist.
     *
     * @param taskList    contains all wish task.
     * @param uiManager   assist in printing exit message.
     * @param fileManager manages the file.
     */
    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) {
        uiManager.printList(taskList);
    }
}
