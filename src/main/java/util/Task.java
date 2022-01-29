package util;

public class Task {
    /** Description of a task */
    protected String description;

    /** Completion status of a task */
    protected boolean isDone;

    /** Total number of tasks currently in use */
    protected static int count = 0;

    /**
     * Class constructor
     *
     * @param description the Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
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
        return (isDone() ? "X" : " ");
    }

    /**
     * Returns the total number of Task instances currently in use.
     *
     * @return number of tasks.
     */
    public static int getCount() {
        return count;
    }

    /**
     * Overrides the toString function with formatted details of the task.
     */
    @Override
    public String toString() {
        return ("[" + getStatusSymbol() + "] " + getDescription());
    }
}
