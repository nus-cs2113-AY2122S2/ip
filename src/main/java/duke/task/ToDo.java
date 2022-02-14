package duke.task;
/**
 * Represents a todo item for the user
 */
public class ToDo extends Task {
    /**
     * Constructs the todo item
     *
     * @param description
     *            description of the todo item
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs the todo item
     *
     * @param description
     *            description of the todo item
     * @param isDone
     *            status of the todo item
     */
    public ToDo(String trim, boolean equals) {
        super();
    }

    public String getTask() {
        return "[T]" + super.getTask();
    }
}
