package tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markTaskAsDone() {
        this.setDone(true);
    }

    public void markTaskAsUndone() {
        this.setDone(false);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String getDetails();

}
