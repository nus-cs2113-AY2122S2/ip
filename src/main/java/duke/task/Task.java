package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeOfTask;

    public Task(String description, String typeOfTask) {
        this.description = description;
        this.isDone = false;
        this.typeOfTask = typeOfTask;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : ""); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}
