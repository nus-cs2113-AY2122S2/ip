public abstract class Task {
    protected String task;
    protected String taskStatus;
    protected String typeOfTask;

    public Task (String task) throws NoTaskException {
        this.task = task.trim();
        this.taskStatus = "[ ]";

        if (this.task == null) {
            throw new NoTaskException();
        }
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
