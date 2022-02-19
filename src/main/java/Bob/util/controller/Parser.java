package bob.util.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A helper that handles the parsing of user inputs.
 */
public class Parser {

    public static final String UNKNOWN_DATE_FORMAT = "Unknown format, use yyyy-mm-dd, setting default date.";
    public static final String[] POSSIBLE_FORMAT_ONE = {"yyyy-MM-dd", "yyyy/MM/dd", "E, MMM dd yyyy",
            "dd/MM/yyyy", "dd-MM-yyyy", "MM/dd/yyyy", "MM-dd-yyyy"};
    public static final int COMMAND_TOKEN_LENGTH = 2;

    /**
     * Takes in a command delimited by a space and parses it into 2 trimmed tokens.
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
     * Parses the date string and attempts to match it through possible formats.
     * A default date that is 1 month ahead of the current will be returned if issues detected.
     *
     * @param date a Date string.
     * @return a correct date without any formatting issues.
     */
    public static LocalDate parseDate(String date) {
        LocalDate parsedDate = LocalDate.MIN;
        for (String dateFormats : POSSIBLE_FORMAT_ONE) {
            try {
                DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormats);
                parsedDate = LocalDate.parse(date, format);
                // breaks loop if format parsed successfully
                break;
            } catch (DateTimeParseException e) {
                // caught to continue loop to test formats
            }
        }
        if (parsedDate == LocalDate.MIN) {
            UI.printlnTab(UNKNOWN_DATE_FORMAT);
            return LocalDate.now().plusMonths(1);
        }
        return parsedDate;
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
