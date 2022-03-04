package main.java.duke.command;

import main.java.duke.ui.Ui;

/**
 * Class for ListCommand. It is created when the user requests for the task list.
 */

public class ListCommand extends Command {

    /**
     * Method to carry out the command. It calls Ui to print out the tasks in the list.
     */
    public void execute() {
        Ui.printList();
    }
}