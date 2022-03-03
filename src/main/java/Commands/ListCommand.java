package Commands;

import Interfaces.UI;
import Managers.TaskManager;

import static Constants.BaoConstants.LINE_BREAK;

/**
 * Command for Bao to display all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Calls <code>TaskManager</code> method to display all tasks.
     *
     * @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        System.out.print(LINE_BREAK);
        System.out.println("Here are your tasks:");
        taskManager.listTasks();
        System.out.print(LINE_BREAK);
    }
}
