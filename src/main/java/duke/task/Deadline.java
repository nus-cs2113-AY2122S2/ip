package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in Duke.
 */
public class Deadline extends Task {

    protected LocalDate datetime;

    /**
     * Assumption: Every field must be present and not null.
     */
    public Deadline(String description, LocalDate datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     * Assumption: Every field must be present and not null.
     */
    public Deadline(String description, Boolean isDone, LocalDate datetime) {
        super(description, isDone);
        this.datetime = datetime;
    }

    /**
     * Returns the Task's type in String.
     *
     * @return The string "deadline".
     */
    @Override
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Returns the deadline for the task in formatted String.
     *
     * @return The deadline in String format.
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
     * @return deadline of task in unformatted String.
     */
    @Override
    public String getDateForStorageFile() {
        return datetime.toString();
    }

    /**
     * Returns the whole string
     * including the task icon, task status,
     * task description and deadline to be printed.
     *
     * @return The task icon, task status, task description and deadline in String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateFormattedString() + ")";
    }
}
