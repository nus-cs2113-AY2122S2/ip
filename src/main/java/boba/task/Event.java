package boba.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent a task with a set time to meet.
 * Inherits from the Task class.
 */
public class Event extends Task {

    /** The time the event is occurring*/
    protected String at;
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
            date = LocalDate.parse("0000-01-01");
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
        String formatted = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + formatted + ")";
    }
}
