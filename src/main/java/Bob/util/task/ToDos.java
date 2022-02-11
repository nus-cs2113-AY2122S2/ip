package bob.util.task;

public class ToDos extends Task {
    /**
     * Class constructor
     *
     * @param description the Task description.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Class constructor
     *
     * @param description the Task description.
     * @param isDone      the completion status.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides the toString function with formatted details of the deadline.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
