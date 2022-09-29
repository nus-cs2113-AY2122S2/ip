package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon.
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Converts task to string.
     * @return Task in string.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts task to string to be saved.
     * @return String to be saved.
     */
    public String toSave() {
        return (isDone ? "1" : "0") + "," + this.description;
    }
}