package main.java.duke.command;

import main.java.duke.exception.DukeException;
import main.java.duke.ui.Ui;
import java.time.LocalDate;

/**
 * Class for the CheckDateCommand. It is created when the user wants to check if a date
 * has any task.
 */

public class CheckDateCommand extends Command {

    private final String date;

    /**
     * Constructor for CheckDateCommand.
     * 
     * @param date String date that is being checked in the format DD/MM/YYYY
     */
    public CheckDateCommand(String date) {
        this.date = date;
    }
    
    /**
     * Method to carry out the command. Calls a Ui method to show tasks on the date.
     * 
     * @throws DukeException If user input is invalid.
     */
    public void execute() throws DukeException {
        if (isValidDate(convertDate(this.date))) {
            LocalDate localDate = LocalDate.parse(convertDate(this.date));
            Ui.printCheckDate(localDate);

        } else {
            throw new DukeException("Oh no! You have typed an invalid date!\n" +
            "The format for date is DD/MM/YYYY !, e.g. 15/02/2022");
        }
    }
}