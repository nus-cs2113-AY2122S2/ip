package bob.util.task;

import bob.util.controller.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a happening that will occur at a certain period.
 */
public class Events extends Task {
    /** Period of a task */
    protected LocalDate period;

    /**
     * Class constructor
     *
     * @param description the Task description.
     * @param period      the Task period.
     */
    public Events(String description, LocalDate period) {
        super(description);
        this.period = period;
    }

    /**
     * Class constructor
     *
     * @param description the Task description.
     * @param isDone      the completion status.
     * @param period      the Task period.
     */
    public Events(String description, boolean isDone, LocalDate period) {
        super(description, isDone);
        this.period = period;
    }

    /**
     * Returns the task's period.
     *
     * @return a period text.
     */
    public String getPeriod() {
        return period.format(DateTimeFormatter.ofPattern(UI.DATE_FORMAT));
    }

    /**
     * Overrides the toString function with formatted details of the event.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + getPeriod() + ")");
    }
}
