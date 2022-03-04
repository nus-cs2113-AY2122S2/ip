package main.java.duke.command;

import main.java.duke.ui.Ui;

/**
 * Class for CommandsCommand. It is created when a user request for the command list.
 */

public class CommandsCommand extends Command {

    /**
     * Method to carry out the command. Calls Ui to print out all valid commands into a list.
     */
    public void execute() {
        Ui.printCommand();
    }
}