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
     * @param task       The task to complete.
     * @param isDone     Whether the task is done.
     * @param completeBy When the task is due by.
     */
    public Deadline(String task, boolean isDone, String completeBy) {
        super("D", isDone, task);
        if (completeBy == null) {
            throw new IllegalArgumentException(String.format("No `%s` argument specified!", REQ_ARG));
        }
        this.completeBy = completeBy;
    }

    /**
     * Attempts to unmarshal a storage-friendly parts string into a Deadline object.
     *
     * @param parts A storage-friendly string split into parts.
     * @return Parsed Deadline object.
     */
    public static Deadline unMarshal(String[] parts) {
        return new Deadline(parts[2], Boolean.parseBoolean(parts[1]), parts[3]);
    }

    @Override
    public String marshal() {
        return String.format("%s | %s", super.marshal(), this.completeBy);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.completeBy);
    }
}
