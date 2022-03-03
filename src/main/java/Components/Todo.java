package Components;

/**
 * Contains a task.
 */
public class Todo extends Task {
    /**
     * Creates a <code>Todo</code> object with specified task description.
     *
     * @param description Description of task.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns task information.
     *
     * @return String containing task information.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
