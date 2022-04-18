package duke.data.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static duke.common.Strings.*;

/**
 * Represents an event occurring at a given date or time.
 */
public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
        try {
            savedDate = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(FORMAT_DATETIME)).toLocalDate();
        } catch (DateTimeParseException e) {
            try {
                savedDate = LocalDate.parse(time, DateTimeFormatter.ofPattern(FORMAT_DATE));
            } catch (DateTimeParseException f) {
                savedDate = null;
            }
        }
    }

    @Override
    public String formatAsData(String FS) {
        return "E" + FS + super.formatAsData(FS) + FS + this.time;
    }

    @Override
    public String toString() {
        if (savedDate != null) {
            return "EVENT: " + super.toString() + " (at: " + this.time + ")^";
        }
        return "EVENT: " + super.toString() + " (at: " + this.time + ")";
    }
}
