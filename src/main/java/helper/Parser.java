package helper;

import taskitems.exceptions.DateException;
import taskitems.exceptions.IllegalInputException;
import taskitems.exceptions.TimeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Parser {

    public String parseTodo (String reader) throws IllegalInputException {
        String parameter = reader;
        if (!parameter.equals("")) {
            return parameter;
        }
        throw new IllegalInputException();
    }

    public String[] parseDeadline (String reader) throws IllegalInputException {
        String[] parameter = reader.trim().split(" /by ");
        if (parameter.length != 2) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (parameter[0].equals("") || parameter[1].equals("")) {
            throw new IllegalInputException();
        }
        return parameter;
    }

    public String[] parseEvent (String reader) throws IllegalInputException, ArrayIndexOutOfBoundsException  {
        String[] parameter = reader.trim().split(" /at ");
        if (parameter.length != 2) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (parameter[0].equals("") || parameter[1].equals("")) {
            throw new IllegalInputException();
        }
        return parameter;
    }
    public int parseMark (String reader) throws NumberFormatException {
        String parameter = reader.trim();
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException();
        }
    }

    public int parseDelete (String reader) {
        String parameter = reader.trim();
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException();
        }
    }

    public LocalDate parseDate (String date) throws DateException {
        try {
            LocalDate finalDate = LocalDate.parse(date);
            return finalDate;
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DateException();
        }
    }

    public LocalTime parseTime (String time) throws TimeException {
        try {
            LocalTime finalTIme = LocalTime.parse(time);
            return finalTIme;
        } catch (DateTimeParseException dateTimeParseException) {
            throw new TimeException();
        }
    }








}
