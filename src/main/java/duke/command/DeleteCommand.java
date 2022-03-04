package main.java.duke.command;

import main.java.duke.task.Task;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;

/**
 * Class for DeleteCommand. It is created when the user wants to delete a task from the task list.
 */

public class DeleteCommand extends Command {

    private final int deleteInt;

    /**
     * Constructor for DeleteCommand.
     * 
     * @param deleteInt The task of number deleteInt that the user wants to remove.
     */
    public DeleteCommand(int deleteInt) {
        this.deleteInt = deleteInt;
    }

    /**
     * Method to carry out the command. It gets the task to be removed from the task list, and 
     * calls the Ui command to show the task that has been deleted.
     */
    public void execute() {
        Task task = Duke.tasks.get(deleteInt - 1);
        Duke.tasks.remove(deleteInt - 1);
        Duke.taskCounter--;
        Ui.printDelete(task);
    }
}