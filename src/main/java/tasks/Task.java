package tasks;

/**
 * Base abstract class for storing all kinds of tasks.
 */
public abstract class Task {
    /**
     * The type of task.
     */
    public final String type;

    /**
     * The actual task description.
     */
    public final String taskDescription;

    /**
     * Checks whether the item is done.
     */
    public boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param type            Type of task.
     * @param isDone          Whether the task is done.
     * @param taskDescription Task description.
     */
    public Task(String type, boolean isDone, String taskDescription) throws IllegalArgumentException {
        this.type = type;
        if (taskDescription == null || taskDescription.isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty!");
        }
        this.isDone = isDone;
        this.taskDescription = taskDescription;
    }

    /**
     * Sets whether the task is marked as done or not.
     *
     * @param done New marked status.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns a storage-friendly string representation of the task object.
     *
     * @return Storage-friendly string representation of object.
     */
    public String marshal() {
        return String.format("%s | %b | %s", this.type, this.isDone, this.taskDescription);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type, this.isDone ? "X" : " ", this.taskDescription);
    }
}
