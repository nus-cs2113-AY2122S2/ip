package serene.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with provided description.
     *
     * @param description The task's description.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns a character depending on whether the task is marked.
     *
     * @return "X" if task is marked, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns if the task is marked.
     *
     * @return True if task is marked, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void markNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
