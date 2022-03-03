package Commands;

import Exceptions.BadIndexException;
import Exceptions.MaxTaskException;
import Interfaces.UI;
import Managers.TaskManager;

public abstract class Command {
    private boolean isExit = false;

    public abstract void execute(TaskManager taskManager, UI ui) throws BadIndexException, NumberFormatException,
            MaxTaskException;

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }
}
