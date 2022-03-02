package Commands;

import Interfaces.UI;
import Managers.TaskManager;

import static Constants.BaoConstants.LINE_BREAK;

public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        System.out.print(LINE_BREAK);
        System.out.println("Here are your tasks:");
        taskManager.listTasks();
        System.out.print(LINE_BREAK);
    }
}
