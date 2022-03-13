package util.task;

/**
 * Represents a general task
 */
public abstract class Task {
    protected String task;
    protected String taskStatus;
    protected String typeOfTask;

    /**
     * Initialize the task object
     *
     * @param task The task user adds
     */
    public Task (String task) {
        this.task = task.trim();
        this.taskStatus = "[ ]";
        this.typeOfTask = "[ ]";
    }

    /**
     * Get the task
     *
     * @return The task in string format
     */
    public String getTask() {
        return task;
    }

    /**
     * Mark the task as done
     */
    public void mark() {
        this.taskStatus = "[X]";
    }

    /**
     * Unmark the task
     */
    public void unmark() {
        this.taskStatus = "[ ]";
    }

    /**
     * Turn the whole task object to string format
     *
     * @return the task object instring format
     */
    public String toString() {
        return typeOfTask + taskStatus + " " + task;
    }
}
