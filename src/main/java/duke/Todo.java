package duke;

/**
 * Represents a basic todo instance of a task without any extra attributes.
 */
public class Todo extends Task{

    /**
     * Creates an instance of a Todo class
     * @param title Title of a todo instance
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * @return String representation of a todo object and its attributes.
     */
    @Override
    public String toString()
    {
        return "[T]" + super.toString();
    }
}
