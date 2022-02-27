package Tasks;

/**
 * Represents a kind of task without any date/time attached to it.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getType() {
        return "T";
    }
}