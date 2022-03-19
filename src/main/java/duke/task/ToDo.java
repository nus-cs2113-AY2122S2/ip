package duke.task;

/**
 * Example: todo read book
 * description: "read book"
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
