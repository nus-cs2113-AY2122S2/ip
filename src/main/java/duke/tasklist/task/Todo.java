package duke.tasklist.task;

/**
 * The Todo object.
 * A subclass of Task.
 */
public class Todo extends Task {

    /**
     * Initializes a new Todo object.
     *
     * @param description Todo description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
