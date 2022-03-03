package Commands;

import Exceptions.BadIndexException;
import Interfaces.UI;
import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) throws BadIndexException, NumberFormatException {
        try {
            printWithLine(taskManager.markTask(index),
                    "Boom! Another task done already???");
        } catch (Exception e) {
            throw e;
        }
    }
}
