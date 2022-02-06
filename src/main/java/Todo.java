public class Todo extends Task {

    public Todo(String task) {
        super(task);
        this.typeOfTask = "[T]";
    }

    @Override
    public String toString() {
        return typeOfTask + taskStatus + " " + task;
    }
}
