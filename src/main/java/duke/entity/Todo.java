package duke.entity;

/**
 * Represents a user task of Todo type.
 */
public class Todo extends Task {

    private final static String TODO_MARKER = "[T]";
    /**
     * Creates Todo.
     *
     * @param description Task description
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Creates Todo and marks Todo according to specified completion status
     *
     * @param description Task description
     * @param isTaskDone Task Completion Status
     */
    public Todo(String description, boolean isTaskDone) {
        super(description, isTaskDone);
    }
    /**
     * Returns Description of Todo with completion status.
     * @return String of Todo description and completion status.
     */
    @Override
    public String toString() {
        return TODO_MARKER + super.toString();
    }
}