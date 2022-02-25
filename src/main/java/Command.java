import java.io.IOException;
import java.util.ArrayList;

abstract public class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    public abstract void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) throws DukeWrongCommandException;

    protected void updateToFile(String fileName, ArrayList<Task> taskList, TaskFileManager fileManager) {
        try {
            fileManager.saveTaskList(fileName, taskList);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

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
