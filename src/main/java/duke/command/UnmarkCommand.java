package main.java.duke.command;

import main.java.duke.task.Task;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;

/**
 * Class for UnmarkCommand. It is created when the user requests to unmark a task.
 */

public class UnmarkCommand extends Command {
    
    private final int markInt;

    /**
     * Constructor for UnmarkCommand.
     * 
     * @param markInt The task of number markInt in the task list that the user wants to unmark.
     */
    public UnmarkCommand(int markInt) {
        this.markInt = markInt;
    }

    /**
     * Method to carry out the command. It unmarks the task and calls Ui let the user know the
     * task has been unmarked.
     */
    public void execute() {
        Task task = Duke.tasks.get(markInt - 1);
        task.setDone(false);
        Ui.printUnmark(markInt, task);
    }
}