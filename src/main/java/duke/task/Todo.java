package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    // Prints the todo task in a fixed format
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
