package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    public String getType() {
        return type;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
