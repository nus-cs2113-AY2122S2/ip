package Commands;

import Interfaces.UI;

import Managers.TaskManager;

/**
 * For all commands that are not implemented in Bao.
 */
public class UnknownCommand extends Command {
    /**
     * Display error message. Argument taskManager is not used.
     *
     * @param taskManager Not used. May be <code>null</code>.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        ui.unknownCommandMessage();
    }
}
