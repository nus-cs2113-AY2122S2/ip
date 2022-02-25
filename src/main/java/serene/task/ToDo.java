package serene.task;

public class ToDo extends Task {
    /**
     * Constructs a new ToDo with provided description.
     *
     * @param description The task's description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
