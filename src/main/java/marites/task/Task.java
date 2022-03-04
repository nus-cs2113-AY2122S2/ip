package marites.task;

/** A base class for tasks. */
public abstract class Task implements java.io.Serializable {
    private final String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task.
     * @return Whether or not the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the status of the task
     * @param done The new status.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns True iff the query matches the given task.
     * @param query A string to query
     * @return Whether or not this task's description matches.
     */
    public boolean isMatch(String query) {
        return description.contains(query);
    }

    @Override
    public String toString() {
        String doneIndicator = (isDone ? "[X]" : "[ ]");
        return String.format("%s %s", doneIndicator, this.description);
    }
}
