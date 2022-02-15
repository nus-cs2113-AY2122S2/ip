package util.task;

public abstract class Task {
    protected String task;
    protected String taskStatus;
    protected String typeOfTask;

    public Task (String task) {
        this.task = task.trim();
        this.taskStatus = "[ ]";
    }

    public String getTask() {
        return task;
    }

    public String getTime() {
        return null;
    }

    public void mark() {
        this.taskStatus = "[X]";
        this.typeOfTask = "[ ]";
    }

    public void unmark() {
        this.taskStatus = "[ ]";
    }

    public String toString() {
        return typeOfTask + taskStatus + " " + task;
    }
}
