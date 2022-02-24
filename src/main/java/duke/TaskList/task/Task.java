package duke.TaskList.task;

/**
 * Describes the Task object; it is an abstract class that
 * is the superclass of Todo, Deadline, and Event.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return String.format(" [%s] %s", this.getStatusIcon(), this.description);
    }
}
