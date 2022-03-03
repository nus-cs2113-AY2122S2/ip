package Commands;

import Interfaces.UI;
import Managers.TaskManager;

/**
 * Command to end Bao's execution.
 */
public class ExitCommand extends Command {
    /**
     * Creates exit command.
     */
    public ExitCommand() {
        super.setExit(true);
    }

    /**
     * Empty method. Argument taskManager is not used.
     *
     * @param taskManager Not used. May be <code>null</code>.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        ui.farewell();
    }
}
