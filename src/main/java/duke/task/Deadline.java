package duke.task;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task{
    private static final String DEADLINE_LOGO = "[D]";
    private static final String BY_FIRST_HALF = " (by: ";
    private static final String BY_SECOND_HALF = ")";

    protected String by;

    public Deadline(String description, String by, String typeOfTask) {
        super(description, typeOfTask);
        this.by = by;
    }

    /**
     * This is the getBy method that returns the deadline of this deadline task.
     *
     * @return The deadline of this deadline task.
     */
    public String getBy() {
        return by;
    }

    /**
     * This is the toString method that returns a particular format to represent this Event task.
     *
     * @return The printing format of this Event task.
     */
    @Override
    public String toString() {
        return DEADLINE_LOGO + super.toString() + BY_FIRST_HALF + by + BY_SECOND_HALF;
    }
}
