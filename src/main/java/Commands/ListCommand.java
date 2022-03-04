package Commands;

import Interfaces.UI;
import Managers.TaskManager;

/**
 * Command for Bao to display all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Calls <code>TaskManager</code> method to display all tasks. Argument taskManager is not used directly.
     *
     * @param taskManager Not used directly. May be <code>null</code>.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        ui.listTasksMessage();
    }
}
