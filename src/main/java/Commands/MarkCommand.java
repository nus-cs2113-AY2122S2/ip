package Commands;

import Interfaces.UI;

import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

/**
 * Command for Bao to mark a task in the task list as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates mark command for specified task.
     *
     * @param index Index (in task list) of task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Calls <code>TaskManager</code> method to mark task and displays the task that was marked.
     *
     * @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        try {
            printWithLine(taskManager.markTask(index),
                    "Boom! Another task done already???");
        } catch (NumberFormatException e) {
            printWithLine("So close! You just need to provide me the task number to mark.");
        } catch (IndexOutOfBoundsException e) {
            printWithLine("I've checked and double checked. There is no such task.");
        }
    }
}
