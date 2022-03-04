package duke.tasklist.task;

/**
 * The Deadline object.
 * A subclass of Task.
 */
public class Deadline extends Task{
    private String by;

    /**
     * Initializes a new Deadline object.
     *
     * @param description Deadline description.
     * @param by Deadline time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the time of a Deadline object.
     *
     * @return by Deadline time.
     */
    public String getByTime() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
