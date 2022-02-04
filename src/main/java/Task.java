public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task markDone() {
        return new Task(description, true);
    }

    public Task markUndone() {
        return new Task(description);
    }

    public String getStatusIcon() {
        return ("[" + (isDone ? "X" : " ") + "] "); // mark done task with X
    }

    @Override
    public String toString() {
        return description;
    }
}