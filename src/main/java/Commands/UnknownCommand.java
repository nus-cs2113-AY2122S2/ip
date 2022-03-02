package Commands;

import Interfaces.UI;
import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        printWithLine("I do not understand that yet!");
    }
}
