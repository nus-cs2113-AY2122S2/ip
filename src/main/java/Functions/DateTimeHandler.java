package Functions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeHandler {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static LocalDateTime dateTimeParse(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public static String toString(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}
