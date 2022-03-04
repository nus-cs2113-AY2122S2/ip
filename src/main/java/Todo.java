/**
 * Represents a wish task. This wish is called "todo"
 * tasks without any date/time attached to it.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * @return indication of wish task for the file.
     */
    @Override
    public String toFileString() {
        return "T " + super.toFileString();
    }

    /**
     * @return indication of wish task for the user interface.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
