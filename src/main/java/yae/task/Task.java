package yae.task;

public abstract class Task {
    protected final String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public abstract String getTaskType();

    public abstract String getTime();

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    @Override
    public String toString() {
        return getStatusIcon() + taskDescription;
    }
}
