package Commands;

import Interfaces.UI;
import Managers.TaskManager;

public abstract class Command {
    private boolean isExit = false;

    public abstract void execute(TaskManager taskManager, UI ui);

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }
}
