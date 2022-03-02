package solana.task;

/**
 * Represents the Deadline task. Deadline tasks are tasks that need to be done by a specific date/time.
 */
public class Deadline extends Task {
    protected String byDate;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns the Deadline class as a string format.
     *
     * @return String format.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[D][X] " + description + " (By: " + byDate + ")";
        } else {
            return "[D][ ] " + description + " (By: " + byDate + ")";
        }
    }
}
