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
    public String completeBy;

    /**
     * Creates a new Deadline.
     *
     * @param task The task to complete.
     * @param completeBy   When the task is due by.
     */
    public Deadline(String task, String completeBy) {
        super("D", task);
        if (completeBy == null) {
            throw new IllegalArgumentException(String.format("No `%s` argument specified!", REQ_ARG));
        }
        this.completeBy = completeBy;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.completeBy);
    }
}
