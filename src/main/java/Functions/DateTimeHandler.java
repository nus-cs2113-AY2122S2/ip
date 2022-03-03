package Functions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles date and time conversion between <code>LocalDateTime</code> and String.
 */
public class DateTimeHandler {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates <code>LocalDateTime</code> object from String input.
     *
     * @param dateTimeString String input of format "dd/mm/yyyy HHmm".
     * @return LocalDateTime object.
     * @throws DateTimeParseException invalid format is used.
     */
    public static LocalDateTime dateTimeParse(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Creates String from <code>LocalDateTime</code> object.
     *
     * @param dateTime LocalDateTime object.
     * @return String of format "dd/mm/yyyy HHmm".
     */
    public static String toString(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}
