package Components;

public class Task {
    private String description;
    private boolean isDone;

    public Task() {
        this.description = null;
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // mark done task with X, undone task with space
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}

