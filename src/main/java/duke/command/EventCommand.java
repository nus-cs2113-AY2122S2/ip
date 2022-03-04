package main.java.duke.command;

import main.java.duke.task.Event;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;
import main.java.duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for EventCommand. It is created when the user wants to create a new Event.
 */

public class EventCommand extends Command {
    
    private String input;

    /**
     * Constructor for EventCommand.
     * 
     * @param input User input.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Method to check if user input dates are valid.
     * 
     * @param startDate The start date in YYYY-MM-DD.
     * @param startTime The start time in 24-hour format.
     * @param endDate The end date in YYYY-MM-DD.
     * @param endTime The end time in 24-hour format.
     * @return Boolean value whether the input dates are valid.
     */
    private boolean isValidDates(String startDate, String startTime, 
            String endDate, String endTime) {
        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate endLocalDate = LocalDate.parse(endDate);
        if (startLocalDate.isBefore(endLocalDate)) {
            return true;

        } else if (startLocalDate.equals(endLocalDate) &&
                Integer.parseInt(endTime) > Integer.parseInt(startTime)) {
            return true;

        } else {
            return false;

        }
    }

    /**
     * Method to carry out the command. It checks if user input is valid at various points. If
     * valid, it creates an Event and adds it onto the task list.
     * 
     * @throws DukeException If user input is invalid.
     */
    public void execute() throws DukeException {
        String[] splitString = input.split(" /at ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need to include a date after '/at'!");
        } else if (splitString[0].equals("")) {
            throw new DukeException("Oh no! You need a description for this event!");
        } else {
            String date = splitString[1];
            if (date.contains(" /to ")) {
                String[] timings = date.split(" /to ");
                if (timings.length < 2) {
                    throw new DukeException("Oh no! You need to include a date after '/to'!");
                } else if (timings[0].equals("")) {
                    throw new DukeException("Oh no! You need to include a date before '/to'!");
                } else {
                    String startDate = convertDate(timings[0]);
                    String startTime = convertTime(timings[0]);
                    String endDate = convertDate(timings[1]);
                    String endTime = convertTime(timings[1]);
                    if (isValidDates(startDate, startTime, endDate, endTime)) {
                        Event task = new Event(splitString[0], startDate, 
                                startTime, endDate, endTime);
                        Duke.tasks.add(task);
                        Duke.taskCounter++;
                        Ui.printTask(task);
                    } else {
                        throw new DukeException("Oh no! Your ending date is after " +
                                "your starting date!");
                    }
                }
            } else {
                throw new DukeException("Oh no! You need to include a '/to' between"  +
                        " your start and end time!");
            }
            
        }
    }
}