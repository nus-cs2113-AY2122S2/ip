/**
 * Represent an item to do.
 * Inherits from the Task class.
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
     * @return String representation of Todo Class
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
