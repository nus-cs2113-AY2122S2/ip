package boba.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent a task with a deadline.
 * Inherits from the Task class.
 */
public class Deadline extends Task {

    /** The deadline in which the task needs to be finish by*/
    protected String by;
    private LocalDate date;

    /**
     * Creates a new deadline item
     * @param description A description of the task
     * @param by When the task is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            date = LocalDate.parse("0000-01-01");
        }
    }

    /**
     * Get the by time of the Deadline
     * @return String by
     */
    public String getBy() {
        return by;
    }

    /**
     * @return String representation of Deadline class
     */
    @Override
    public String toString() {
        String formatted = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formatted + ")";
    }
}
