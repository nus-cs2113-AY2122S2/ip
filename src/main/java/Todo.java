/**
 * Represent an item to do.
 * Inherits from the Task class.
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
