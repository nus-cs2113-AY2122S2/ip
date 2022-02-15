package boba.task;

/**
 * Represent a task with a deadline.
 * Inherits from the Task class.
 */
public class Deadline extends Task {

    /** The deadline in which the task needs to be finish by*/
    protected String by;

    /**
     * Creates a new deadline item
     * @param description A description of the task
     * @param by When the task is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
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
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
