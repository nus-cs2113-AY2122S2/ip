package Commands;

import Exceptions.MaxTaskException;
import Interfaces.UI;
import Managers.TaskManager;

/**
 * Abstract base class for all commands that can be executed by Bao.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Performs all actions required of the command.
     *  @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
    public abstract void execute(TaskManager taskManager, UI ui) throws IndexOutOfBoundsException, NumberFormatException,
            MaxTaskException;

    /**
     * @return <code>true</code> if command is used to exit Bao app, <code>false</code> otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * @param isExit <code>true</code> if command is used to exit Bao app, <code>false</code> otherwise.
     */
    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }
}
