package Commands;

import Interfaces.UI;
import Managers.TaskManager;

public class ExitCommand extends Command {
    public ExitCommand() {
        super.setExit(true);
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) {

    }
}
