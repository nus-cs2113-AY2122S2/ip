package Components;

import Functions.DateTimeHandler;

import java.time.LocalDateTime;

/**
 * Contains a task and a deadline for the task to be completed by.
 */
public class Deadline extends Task {
    private LocalDateTime dateTime;

    /**
     * Creates a <code>Deadline</code> object with specified task description and deadline.
     *
     * @param description Description of task.
     * @param dateTime Deadline of the task. String input.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = DateTimeHandler.dateTimeParse(dateTime);
    }

    /**
     * @return Deadline of the task.
     */
    public String getDateTime() {
        return DateTimeHandler.toString(dateTime);
    }

    /**
     * Returns task information.
     *
     * @return String containing task information.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime() + ")";
    }
}
