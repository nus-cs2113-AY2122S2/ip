package util.task;

public abstract class Task {
    protected String task;
    protected String taskStatus;
    protected String typeOfTask;

    public Task (String task) {
        this.task = task.trim();
        this.taskStatus = "[ ]";
        this.typeOfTask = "[ ]";
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
