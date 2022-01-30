public class Todo {
    public String typeOfTask;
    public String taskStatus;
    public String task;

    public Todo(String task) {
        this.typeOfTask = "[T]";
        this.taskStatus = "[ ]";
        this.task = task.trim();
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
