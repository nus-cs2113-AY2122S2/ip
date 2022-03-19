package tasks;

/**
 * parent class for all types of tasks
 */
public class Task {
    public String description;
    protected boolean isDone;

    /**
     * constructor
     *
     * @param description
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * mark a task as done status
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * change a task status to undone
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * overwrite the print function
     *
     * @return
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}