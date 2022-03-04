import java.io.IOException;
import java.util.ArrayList;

/**
 * Loads and update the taskfile and executes the
 * required actions.
 */
abstract public class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    public abstract void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) throws DukeWrongCommandException;

    /**
     * Method to update the content of the file.
     *
     * @param fileName    name of file.
     * @param taskList    contains list of task.
     * @param fileManager for updating to file.
     */
    protected void updateToFile(String fileName, ArrayList<Task> taskList, TaskFileManager fileManager) {
        try {
            fileManager.saveTaskList(fileName, taskList);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    /**
     * Method to load the content to the file.
     *
     * @param fileName    name of file.
     * @param taskList    contains list of task.
     * @param fileManager for updating to file.
     */
    protected void loadTaskFile(String fileName, ArrayList<Task> taskList, TaskFileManager fileManager) {
        try {
            fileManager.loadTaskList(fileName, taskList);
        } catch (IOException error) {
            System.out.println("--not valid file--");
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}
