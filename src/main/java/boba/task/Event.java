package boba.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent a task with a set time to meet.
 * Inherits from the Task class.
 */
public class Event extends Task {

    private static final String INVALID_DATE = "0000-01-01";

    /** The time the event is occurring*/
    private String at;

    /** Date for format of (YYYY-MM-DD) */
    private LocalDate date;

    /**
     * Creates a new event item
     * @param description A description of the task
     * @param at When the event is occurring
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            date = LocalDate.parse(INVALID_DATE);
        }
    }

    /**
     * Get the at time of the Event
     * @return String at
     */
    public String getAt() {
        return at;
    }

    /**
     * @return String representation of Event Class
     */
    @Override
    public String toString() {
        if (date.toString().equals(INVALID_DATE)) {
            return "[D]" + super.toString() + " (at: " + at + ")";
        } else {
            String formatted = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + super.toString() + " (at: " + formatted + ")";
        }
    }
}
