public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String taskIcon = "[T]";
        return taskIcon + super.toString();
    }
}
