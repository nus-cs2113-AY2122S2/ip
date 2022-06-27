package shrek.task;

/**
 * Represents a Todo task.
 */
public class ToDo extends Task {
    /**
     * Initialises the Todo task.
     *
     * @param description Task that the User has inputted.
     */
    public ToDo(String description) {
        super(description);
        setTaskName("T");
    }
}
