package bob.util.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A helper that handles the parsing of user inputs.
 */
public class Parser {

    public static final String[] POSSIBLE_DATE_FORMATS = {"yyyy-MM-dd", "yyyy/MM/dd", "E, MMM dd yyyy",
            "dd/MM/yyyy", "dd-MM-yyyy", "MM/dd/yyyy", "MM-dd-yyyy"};
    public static final String DELIMITER_EVENT_DATE = "to";
    public static final int COMMAND_TOKEN_LENGTH = 2;
    public static final int DEADLINE_DEFAULT = 1;
    public static final int EVENT_START_DEFAULT = 1;
    public static final int EVENT_END_DEFAULT = 2;

    /**
     * Takes in a command delimited by a delimiter and parses it into 2 trimmed tokens.
     *
     * @param command The command to be parsed.
     * @return A list containing the main command then its details.
     */
    public static String[] parseCommand(String command, String delimiter) {
        String[] commandToken = command.split(delimiter, COMMAND_TOKEN_LENGTH);
        commandToken[0] = commandToken[0].trim();
        if (commandToken.length == COMMAND_TOKEN_LENGTH) {
            commandToken[1] = commandToken[1].trim();
            return commandToken;
        } else {
            return new String[]{commandToken[0], null};
        }
    }

    /**
     * Returns the two event dates in chronological order.
     *
     * @param dates a set of two event dates.
     * @return event dates in chronological order.
     */
    public static LocalDate[] chronologicalEventDates(LocalDate[] dates) {
        LocalDate[] chronDate = new LocalDate[2];
        if (dates[0].isAfter(dates[1])) {
            chronDate[0] = dates[1];
            chronDate[1] = dates[0];
            UI.printlnTab(UI.INCORRECT_DATE_SEQUENCE);
            return chronDate;
        }
        return dates;
    }

    /**
     * Parses the date string and attempts to match it through possible formats.
     * Some default date that will be returned if issues detected.
     *
     * @param date         a Date string.
     * @param defaultMonth a default number of months to be added for issues detected.
     * @return a correct date without any formatting issues.
     */
    public static LocalDate parseDate(String date, int defaultMonth) {
        LocalDate parsedDate = LocalDate.MIN;
        for (String dateFormats : POSSIBLE_DATE_FORMATS) {
            try {
                DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormats);
                parsedDate = LocalDate.parse(date, format);
                // breaks loop if format parsed successfully
                break;
            } catch (DateTimeParseException e) {
                // caught to continue loop to test formats
            } catch (NullPointerException e) {
                break;
            }
        }
        if (parsedDate == LocalDate.MIN) {
            UI.printlnTab(UI.UNKNOWN_DATE_FORMAT);
            return LocalDate.now().plusMonths(defaultMonth);
        }
        return parsedDate;
    }

    /**
     * Parses the date string for an event and attempts to match it through possible formats.
     * A default event period of 1 month ahead of the current will be returned if issues detected.
     *
     * @param date a Date string.
     * @return a correct date period without any formatting issues.
     */
    public static LocalDate[] parseEventDate(String date) {
        LocalDate[] eventDates = new LocalDate[2];
        String[] dateTokens = parseCommand(date, DELIMITER_EVENT_DATE);
        eventDates[0] = parseDate(dateTokens[0], EVENT_START_DEFAULT);
        eventDates[1] = parseDate(dateTokens[1], EVENT_END_DEFAULT);
        eventDates = chronologicalEventDates(eventDates);
        return eventDates;
    }

    /**
     * Parses a deadline string and attempts to match it through possible formats.
     * A default deadline of 1 month ahead of the current will be returned if issues detected.
     *
     * @param date a deadline string.
     * @return a deadline without any formatting issues.
     */
    public static LocalDate parseDeadline(String date) {
        return parseDate(date, DEADLINE_DEFAULT);
    }

    /**
     * Parses an integer string to its integer equivalent.
     *
     * @param text the integer to be formatted.
     * @return an integer formatted text.
     * @throws NumberFormatException if text is not an integer.
     */
    public static int stringToInt(String text) throws NumberFormatException {
        return Integer.parseInt(text);
    }
}
