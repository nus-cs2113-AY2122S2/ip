package Commands;

import Interfaces.UI;

import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

/**
 * Command for Bao to mark a task in the task list as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates unmark command for specified task.
     *
     * @param index Index (in task list) of task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Calls <code>TaskManager</code> method to unmark task and displays the task that was unmarked.
     *
     * @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        try {
            printWithLine(taskManager.unmarkTask(index),
                    "Oh oops, overlooked that one did we?");
        } catch (NumberFormatException e) {
            printWithLine("Don't be embarrassed... What's the task number to unmark.");
        } catch (IndexOutOfBoundsException e) {
            printWithLine("I've checked and double checked. There is no such task.");
        }
    }
}
