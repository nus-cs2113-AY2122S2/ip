package Commands;

import Interfaces.UI;
import Managers.TaskManager;

/**
 * Command to display list of available command formats to the user.
 */
public class HelpCommand extends Command {
    private String command;

    /**
     * Creates help command for specified command keyword.
     *
     * @param command Command to find help for.
     */
    public HelpCommand(String command) {
        this.command = command;
    }

    /**
     * Calls <code>UI</code> method to display help message. Argument taskManager is not used.
     *
     * @param taskManager Not used. May be <code>null</code>.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        ui.helpMessage(command);
    }
}
