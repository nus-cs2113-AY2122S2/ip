package tasks;

/**
 * Todo is a type of Task class
 */
public class Todo extends Task {

    /**
     * constructor
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * overwrite the print function
     *
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
