import java.util.ArrayList;

public class LoadCommand extends Command {
    String loadFileName;

    public LoadCommand(String fileName) {
        loadFileName = fileName;
    }

    /**
     * Execute loading of contents from file to the program.
     *
     * @param taskList    contains all wish task.
     * @param uiManager   assist in printing exit message.
     * @param fileManager manages the file.
     */
    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) {
        loadTaskFile(loadFileName, taskList, fileManager);
    }
}
