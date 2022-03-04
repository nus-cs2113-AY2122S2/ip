package main.java.duke.command;

import main.java.duke.task.Task;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;

/**
 * Class for MarkCommand. It is created when the user requests to mark a task.
 */

public class MarkCommand extends Command {

    private final int markInt;

    /**
     * Constructor for MarkCommand.
     * 
     * @param markInt The task of number markInt in the task list that the user wants to mark.
     */
    public MarkCommand(int markInt) {
        this.markInt = markInt;
    }

    /**
     * Method to carry out the command. It marks the task and calls Ui let the user know the
     * task has been marked.
     */
    public void execute() {
        Task task = Duke.tasks.get(markInt - 1);
        task.setDone(true);
        Ui.printMark(markInt, task);
    }
}