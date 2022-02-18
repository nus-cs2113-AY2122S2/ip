package aeon.task;

/**
 * Represents a task that the users needs to be reminded of in the future, and has no need for any dates
 */
public class Todo extends Task{
    public Todo(String description) {
        super(description);

    }
    @Override
    public String toString() {
        return "[T] " + super.toString() + "\n";
    }
}
