package tasks;

/**
 * Deadline task is a specialisation of the Task class that tracks
 * tasks that need to be done by a specific date and time.
 */
public final class Deadline extends Task {
    /**
     * Specifies the required argument to create a Deadline.
     */
    public static String REQ_ARG = "by";

    /**
     * When the deadline is.
     */
    public String by;

    /**
     * Creates a new Deadline.
     *
     * @param task The task to complete.
     * @param by   When the task is due by.
     */
    public Deadline(String task, String by) {
        super("D", task);
        if (by == null) {
            throw new IllegalArgumentException(String.format("No `%s` argument specified!", REQ_ARG));
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.by);
    }
}
