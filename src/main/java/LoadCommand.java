import java.util.ArrayList;

public class LoadCommand extends Command {
    String loadFileName;

    public LoadCommand(String fileName) {
        loadFileName = fileName;
    }

    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) {
        loadTaskFile(loadFileName, taskList, fileManager);
    }
}
