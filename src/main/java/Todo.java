public class Todo extends Task {
    protected String typeOfTask;
    protected String taskStatus;

    public Todo(String task) {
        super(task);
        this.typeOfTask = "[T]";
        this.taskStatus = "[ ]";
    }

    public void mark() {
        this.taskStatus = "[X]";
    }

    public void unmark() {
        this.taskStatus = "[ ]";
    }

    @Override
    public String toString() {
        return typeOfTask + taskStatus + " " + task;
    }
}
