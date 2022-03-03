package brave.data;

/**
 * Abstract class that act as the parent for Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class Constructor
     * @param description description for the given task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status of the given task.
     *
     * @return "X" if the task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    public abstract String getSaveFormat();
}
