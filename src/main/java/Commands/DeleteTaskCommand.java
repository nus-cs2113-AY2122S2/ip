package Commands;

import Components.Task;
import Exceptions.BadIndexException;
import Interfaces.UI;
import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

public class DeleteTaskCommand extends Command {
    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) throws BadIndexException, NumberFormatException {
        try {
            Task deletedTask = taskManager.deleteTask(index);
            printWithLine("You wanna see a magic trick? Now you see this: " + System.lineSeparator()
                    + deletedTask.toString() + "," + System.lineSeparator()
                    + "AND NOW, you don't!" + System.lineSeparator()
                    + "Check behind your ear, for you still have " + taskManager.getNumTasks() + " tasks in the list.");
        } catch (Exception e) {
            throw e;
        }
    }
}
