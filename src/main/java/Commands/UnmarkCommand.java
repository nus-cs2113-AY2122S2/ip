package Commands;

import Exceptions.BadIndexException;
import Interfaces.UI;
import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) throws BadIndexException, NumberFormatException {
        try {
            printWithLine(taskManager.unmarkTask(index),
                    "Oh oops, overlooked that one did we?");
        } catch (Exception e) {
            throw e;
        }
    }
}
