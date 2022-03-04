package duke.task;

/**
 * Represents a Todo task (eg. todo borrow book)
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    // Representation of Todo in the saved file
    @Override
    public String saveString() {
        return "T" + super.saveString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
