package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Represents a Task of type Deadline. Deadline has task title as well as a string "by" that represents deadline due
 * date.
 * Instead of a string, the due date can also be represented as a date time object "byDate".
 */
public class Deadline extends Task{
    protected String by;
    protected LocalDateTime byDate;

    /**
     * Constructs a Deadline with a title and a string that represents due date.
     * Overloaded constructor.
     * @param title Title of the Deadline task.
     * @param by Deadline due date that is represented as a string.
     */
    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    /**
     * Constructs a Deadline with a title and a datetime object that represents due date.
     * Overloaded constructor.
     * @param title Title of the Deadline task.
     * @param byDate Deadline due date that is represented as a date time object.
     */
    public Deadline(String title, LocalDateTime byDate) {
        super(title);
        this.byDate = byDate;
    }


    /**
     * Gets the string due date of a Deadline.
     * @return String that represents due date of a deadline.
     */
    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Gets the datetime object that represents due date of a deadline.
     * @return Datetime object that represents due date.
     */
    public LocalDateTime getByDate() {
        return byDate;
    }

    public void setByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    /**
     * Formats the bydate as a datetime object.
     * Optionally datetime object can just include a date without time.
     * @return The formatted date time that can be printed.
     */
    public String formatByDate() {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/yyyy[ HH:mm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
        return byDate.format(formatter);
    }

    /**
     * Converts the attributes of a Deadline into a string ready to be printed.
     * @return String that represents a deadline and its attributes.
     */
    @Override
    public String toString() {
        if (getBy() != null) {
            return "[D]" + super.toString() + " (by: " + getBy() + ")";
        }
        else {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("dd/MM/yyyy[ HH:mm]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter();
            return "[D]" + super.toString() + " (by: " + formatByDate() + ")";
        }
    }
}
