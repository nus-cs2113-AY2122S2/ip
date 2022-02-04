public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String taskLabel = "[T]";
        return taskLabel + super.toString();
    }
}
