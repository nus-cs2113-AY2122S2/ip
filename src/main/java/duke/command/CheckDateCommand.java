package main.java.duke.command;

import main.java.duke.exception.DukeException;
import main.java.duke.ui.Ui;
import java.time.LocalDate;

public class CheckDateCommand extends Command {

    private final String date;

    public CheckDateCommand(String date) {
        this.date = date;
    }
    
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