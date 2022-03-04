package main.java.duke.command;

import main.java.duke.ui.Ui;

/**
 * Class for InvalidCommand. It is called when the user inputs an invalid command.
 */

public class InvalidCommand extends Command {

    /**
     * Method to carry out the command. It calls Ui to print out a message to let the user know
     * the command is invalid.
     */
    public void execute() {
        Ui.printInvalid();
    }
}