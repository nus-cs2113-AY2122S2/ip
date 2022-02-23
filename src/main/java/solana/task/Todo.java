package solana.task;

/**
 * Represents the Todo task. Todo tasks are tasks without any date/time attached to it.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the Todo class as a string format.
     *
     * @return String format.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[T][X] " + description;
        } else {
            return "[T][ ] " + description;
        }
    }
}
