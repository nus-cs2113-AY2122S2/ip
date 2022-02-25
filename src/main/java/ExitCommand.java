import java.util.ArrayList;

public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) {
        uiManager.printExitMessage();
    }
}
