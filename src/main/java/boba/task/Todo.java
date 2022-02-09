package boba.task;

/**
 * Represent an item to do.
 * Inherits from the boba.task.Task class.
 */
public class Todo extends Task{

    /**
     * Creates a new todo item
     * @param description A description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @return String representation of boba.task.Todo Class
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
