package bob.util.task;

/**
 * Represents a task that a user has done or wants to carry out.
 */
public abstract class Task {
    /** Description of a task */
    protected String description;

    /** Completion status of a task */
    protected boolean isDone;

    /**
     * Class constructor
     *
     * @param description the Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Class constructor
     *
     * @param description the Task description.
     * @param isDone      the completion status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the task's description.
     *
     * @return a description text.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the boolean completion status of the task.
     *
     * @return true if task completed, else false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Updates the task's description.
     *
     * @param description the Task description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Updates the completion status of the task.
     *
     * @param done the new status of the task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns a symbol indicating if the task has been completed.
     *
     * @return 'X' if task is completed, else blank.
     */
    public String getStatusSymbol() {
        if (isDone()) {
            return "X";
        }
        return " ";
    }

    /**
     * Overrides the toString function with formatted details of the task.
     */
    @Override
    public String toString() {
        return ("[" + getStatusSymbol() + "] " + getDescription());
    }
}
