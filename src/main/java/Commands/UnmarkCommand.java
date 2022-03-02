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
    public void execute(TaskManager taskManager, UI ui) {
        try {
            printWithLine(taskManager.unmarkTask(index),
                    "Oh oops, overlooked that one did we?");
        } catch (NumberFormatException e) {
            printWithLine("Don't be embarrassed... What's the task number to unmark.");
        } catch (BadIndexException e) {
            printWithLine("I've checked and double checked. There is no such task.");
        }
    }
}
