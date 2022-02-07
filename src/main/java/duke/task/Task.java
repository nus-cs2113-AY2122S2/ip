package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTask() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public String toString() {
        return this.isDone ? "1" : "0" + " | " + this.description;
    }
}
