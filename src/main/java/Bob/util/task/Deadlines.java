package bob.util.task;

import bob.util.controller.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */
public class Deadlines extends Task {
    /** Deadline of a task */
    protected LocalDate by;

    /**
     * Class constructor
     *
     * @param description the Task description.
     * @param by          the Task deadline.
     */
    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Class constructor
     *
     * @param description the Task description.
     * @param isDone      the completion status.
     * @param by          the Task deadline.
     */
    public Deadlines(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the task's deadline.
     *
     * @return a deadline text.
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern(UI.DATE_FORMAT));
    }

    /**
     * Overrides the toString function with formatted details of the deadline.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + getBy() + ")");
    }
}
