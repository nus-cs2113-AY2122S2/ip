package duke.entity;

/**
 * Represents a user task of Deadline type.
 */
public class Deadline extends Task {

    private final static String DEADLINE_MARKER = "[D]";
    protected String by;
    /**
     * Creates Deadline and mark as not done
     *
     * @param description Task description
     * @param by Deadline of Task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * Creates Deadline and mark as done according to input.
     *
     * @param description Task description
     * @param by Deadline of Task
     * @param isTaskDone Task Completion Status
     */
    public Deadline(String description, boolean isTaskDone, String by) {
        super(description, isTaskDone);
        this.by = by;
    }
    /**
     * Returns the due date of deadline.
     * @return Deadline of task.
     */
    public String getBy() {
        return by;
    }
    /**
     * Returns Description of deadline with completion status and due date.
     * @return String of deadline description and status.
     */
    @Override
    public String toString() {
        return DEADLINE_MARKER + super.toString() + " (by: " + by + ")";
    }
}