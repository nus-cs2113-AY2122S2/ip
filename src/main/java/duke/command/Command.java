package main.java.duke.command;

import main.java.duke.Duke;
import main.java.duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Command {

    protected boolean isNum(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        } 
        return true;
    }

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

    protected boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

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

    public abstract void execute() throws DukeException;

}