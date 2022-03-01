package main.java.duke.command;

import main.java.duke.ui.Ui;
import main.java.duke.Duke;
import main.java.duke.exception.DukeException;

public class FindCommand implements Command {

    private final String input;

    public FindCommand(String input) {
        this.input = input;
    }

    public void execute() throws DukeException {
        String[] splitString = input.split(" ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need include a keyword to search for");
        } else {
            Ui.printFind(splitString[1]);
        }
    }
}