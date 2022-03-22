package Components;

/**
 * Abstract base class for all tasks that are handled by Bao.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates <code>Task</code> object with the specified task description. Task is set to undone by default.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return "X" if task is done and " " if undone
     */
    private String getStatusIcon() {
        // mark done task with X, undone task with space
        return (isDone ? "X" : " ");
    }

    /**
     * Returns task information.
     *
     * @return String containing task information.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}

