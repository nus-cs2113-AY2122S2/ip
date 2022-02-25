import java.util.ArrayList;

public class ListTaskCommand extends Command {
    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) {
        uiManager.printList(taskList);
    }
}
