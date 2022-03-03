package Commands;

import Components.Task;

import Interfaces.UI;

import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

/**
 * Command for Bao to remove a task from the task list.
 */
public class DeleteTaskCommand extends Command {
    private int index;

    /**
     * Creates delete command for specified task.
     *
     * @param index Index (in task list) of task to be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Calls <code>TaskManager</code> method to delete task and displays the task that was deleted.
     *
     * @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
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
        } catch (IndexOutOfBoundsException e) {
            printWithLine("I've checked and double checked. There is no such task.");
        }
    }
}
