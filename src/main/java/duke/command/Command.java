package main.java.duke.command;

import main.java.duke.Duke;
import main.java.duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Abstract class for all Commands. Contains basic methods for different commands.
 */

public abstract class Command {

    /**
     * Method to check if a String is a number.
     * 
     * @param string String that is being checked.
     * @return Boolean value whether the String is a number.
     */
    protected boolean isNum(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        } 
        return true;
    }

    /**
     * Method to convert a date from DD/MM/YYYY to YYYY-MM-DD.
     * 
     * @param date String date in DD/MM/YYYY time format.
     * @return String date in YYYY-MM-DD format.
     * @throws DukeException If input date format is invalid.
     */
    protected String convertDate(String date) throws DukeException {
        String[] dateTimeArray = date.split(" ");
        String[] dateArray = dateTimeArray[0].split("/");
        if (dateArray.length != 3) {
            throw new DukeException("Oh no! Your date format is invalid!\n" +
                    "The format for date is DD/MM/YYYY !, e.g. 15/02/2022");
        } else if (!isNum(dateArray[0]) || !isNum(dateArray[1]) || !isNum(dateArray[2])) {
            throw new DukeException("Oh no! Your date format is invalid!\n" +
            "The format for date is DD/MM/YYYY !, e.g. 15/02/2022");
        }
        String dayString = String.format("%02d", Integer.parseInt(dateArray[0]));
        String monthString = String.format("%02d", Integer.parseInt(dateArray[1])); 
        String yearString;
        String localDateString;
        if (dateArray[2].length() == 2) {
            yearString = "20" + dateArray[2];
        } else if (dateArray[2].length() == 4) {
            yearString = dateArray[2];
        } else {
            throw new DukeException("Oh no! You have typed an invalid date!\n" +
                    "The format for date is DD/MM/YYYY !, e.g. 15/02/2022");
        }

        localDateString = yearString + "-" + monthString + "-" + dayString;
        if (isValidDate(localDateString)) {
            return localDateString;
        } else {
            throw new DukeException("Oh no! You have typed an invalid date!\n" +
                    "The format for date is DD/MM/YYYY !, e.g. 15/02/2022");
        }
    }

    /**
     * Checks if date in YYYY-MM-DD is a valid date.
     * 
     * @param date String date in YYYY-MM-DD format.
     * @return Boolean value whether date is a valid date.
     */
    protected boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Method that checks if time input is valid.
     * 
     * @param date String date in DD/MM/YYYY time format.
     * @return String time in 24-hour format.
     * @throws DukeException If time is invalid.
     */
    protected String convertTime(String date) throws DukeException {
        String[] dateTimeArray = date.split(" ");
        String time;
        if (dateTimeArray.length < 2) {
            throw new DukeException("Oh no! You did not key in a time");
        } else {
            time = dateTimeArray[1];
            if (isNum(time)) {
                if (Integer.parseInt(time) < 2400 && Integer.parseInt(time) >= 0 &&
                        Integer.parseInt(time) % 100 < 60) {
                    return time;
                } else {
                    throw new DukeException("Oh no! The time you entered is invalid!\n" +
                            "The format for time is in 24-hour format! e.g. 1800");
                }
            } else {
                throw new DukeException("Oh no! The time you entered is invalid!\n" +
                            "The format for time is in 24-hour format! e.g. 1800");
            }
        }
    }

    /**
     * Abstract method that all commands have.
     * 
     * @throws DukeException If command cannot be executed due to invalid inputs.
     */
    public abstract void execute() throws DukeException;

}