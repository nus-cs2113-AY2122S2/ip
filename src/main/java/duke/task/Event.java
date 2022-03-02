package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task in Duke.
 */
public class Event extends Task {

    protected LocalDate datetime;

    /**
     * Assumption: Every field must be present and not null.
     */
    public Event(String description, LocalDate datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     * Assumption: Every field must be present and not null.
     */
    public Event(String description, Boolean isDone, LocalDate datetime) {
        super(description, isDone);
        this.datetime = datetime;
    }

    /**
     * Returns the Task's type in String.
     *
     * @return Returns the string "event".
     */
    @Override
    public String getTaskType() {
        return "event";
    }

    /**
     * Returns the event date for the task in formatted String.
     *
     * @return Returns event date in String format.
     */
    @Override
    public String getDateFormattedString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        String dateFormatString =  datetime.format(dateFormat);
        return dateFormatString;
    }

    /**
     * Returns the Date in its original format as String.
     *
     * @return event date of task in unformatted String.
     */
    @Override
    public String getDateForStorageFile() {
        return datetime.toString();
    }

    /**
     * Returns the whole string
     * including the task icon, task status,
     * task description and event date to be printed.
     *
     * @return The task icon, task status, task description and event date in String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateFormattedString() + ")";
    }
}
