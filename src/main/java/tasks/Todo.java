package tasks;

/**
 * Represents a Task of type Todo. Unlike Deadline and Event tasks, Todo tasks only contains
 * a description of the task (no date and time is stored).
 */
public class Todo extends Task {
    private static final String TODO_CHECKBOX = "[T]";

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo instance.
     *
     * @return A string representation of the Todo instance.
     */
    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s", TODO_CHECKBOX, checkbox, getDescription());
    }
}
