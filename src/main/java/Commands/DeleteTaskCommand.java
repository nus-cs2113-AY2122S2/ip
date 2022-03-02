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
    public void execute(TaskManager taskManager, UI ui) {
        try {
            Task deletedTask = taskManager.deleteTask(index);
            printWithLine("You wanna see a magic trick? Now you see this: " + System.lineSeparator()
                    + deletedTask.toString() + "," + System.lineSeparator()
                    + "AND NOW, you don't!" + System.lineSeparator()
                    + "Check behind your ear, for you still have " + taskManager.getNumTasks() + " tasks in the list.");
        } catch (NumberFormatException e) {
            printWithLine("So close! You just need to provide me the task number to mark.");
        } catch (BadIndexException e) {
            printWithLine("I've checked and double checked. There is no such task.");
        }
    }
}
