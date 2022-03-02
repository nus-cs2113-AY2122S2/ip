package bim.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a date.
 */
public class Event extends Task {
    private LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the date of the Event in 'MMM-dd-yyyy' format
     *
     * @return The date of the event
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDate() + ")";
    }
}
