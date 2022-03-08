package bob.util.task;

import bob.util.controller.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a happening that will occur at a certain period.
 */
public class Events extends Task {
    /** Period of a task */
    protected LocalDate periodStart;
    protected LocalDate periodEnd;

    /**
     * Class constructor
     *
     * @param description the Task description.
     * @param periodStart the start of the Task period.
     * @param periodEnd   the end of the Task period.
     */
    public Events(String description, LocalDate periodStart, LocalDate periodEnd) {
        super(description);
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    /**
     * Class constructor
     *
     * @param description the Task description.
     * @param isDone      the completion status.
     * @param periodStart the start of the Task period.
     * @param periodEnd   the end of the Task period.
     */
    public Events(String description, boolean isDone, LocalDate periodStart, LocalDate periodEnd) {
        super(description, isDone);
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    /**
     * Returns the task's start period.
     *
     * @return a period text.
     */
    public String getPeriodStart() {
        return periodStart.format(DateTimeFormatter.ofPattern(UI.DATE_FORMAT));
    }

    /**
     * Returns the task's period.
     *
     * @return a period text.
     */
    public String getPeriodEnd() {
        return periodEnd.format(DateTimeFormatter.ofPattern(UI.DATE_FORMAT));
    }

    /**
     * Overrides the toString function with formatted details of the event.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: "
                + getPeriodStart() + " to " + getPeriodEnd() + ")");
    }
}
