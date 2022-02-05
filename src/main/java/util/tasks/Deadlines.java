package util.tasks;

public class Deadlines extends Task {
    /** Deadline of a task */
    protected String by;

    /**
     * Class constructor
     *
     * @param description the Task description.
     * @param by          the Task deadline.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the task's deadline.
     *
     * @return a deadline text.
     */
    public String getBy() {
        return by;
    }

    /**
     * Updates the task's deadline.
     *
     * @param by the Task deadline.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Overrides the toString function with formatted details of the deadline.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + getBy() + ")");
    }
}
