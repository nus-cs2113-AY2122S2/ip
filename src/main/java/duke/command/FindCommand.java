package main.java.duke.command;

import main.java.duke.ui.Ui;
import main.java.duke.Duke;
import main.java.duke.exception.DukeException;

/**
 * Class for FindCommand. It is created when the user requests to search for a specific keyword in
 * the tasks in the list.
 */
public class FindCommand extends Command {

    private final String input;

    /**
     * Constructor for FindCommand.
     * 
     * @param input User input.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Method to carry out the command. It calls Ui to search for tasks with description
     * matching the keyword.
     * 
     * @throws DukeException If user input is invalid.
     */
    public void execute() throws DukeException {
        String[] splitString = input.split(" ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need include a keyword to search for");
        } else {
            Ui.printFind(splitString[1]);
        }
    }
}