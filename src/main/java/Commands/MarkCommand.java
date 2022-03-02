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
    public void execute(TaskManager taskManager, UI ui) {
        try {
            printWithLine(taskManager.markTask(index),
                    "Boom! Another task done already???");
        } catch (NumberFormatException e) {
            printWithLine("So close! You just need to provide me the task number to mark.");
        } catch (BadIndexException e) {
            printWithLine("I've checked and double checked. There is no such task.");
        }
    }
}
