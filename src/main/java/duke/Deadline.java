package duke;

/**
 * This class represent a task that need to be completed by the given time.
 */
public class Deadline extends Task {
    protected boolean isDone;
    protected String by;

    /**
     * Store the description and the time of the Deadline object.
     * @param description the description of the given Deadline object.
     * @param by the specific time that the task need to be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.isDone = false;
    }

    /**
     * mark the deadline object as completed.
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * mark the deadline object as incomplete.
     */
    @Override
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * A String representation of the given Deadline object.
     * @return the String representation of the given Deadline object.
     */
    @Override
    public String toString() {
        String status = null;
        if (isDone) {
            status = "X";
        } else {
            status = " ";
        }
        return String.format("[D][%s] %s (by: %s)", status, super.toString().trim(), this.by.trim());
    }
}
