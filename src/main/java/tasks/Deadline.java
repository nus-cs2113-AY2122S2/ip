package tasks;

/**
 * This class handles the creation of Deadline instances.
 */
public class Deadline extends Task {
    private static final String TASK_CODE = "D";
    protected String by;

    /**
     * Creates a Deadline instance with only a task description and its required information.
     *
     * @param taskDescription Description of the deadline task
     * @param by When the deadline is due and any other information
     */
    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Creates a Deadline instance with a task description, its required information
     * and its completion status.
     *
     * @param taskDescription Description of the deadline task
     * @param isCompleted Completion status of the task (ie true if done; false otherwise)
     * @param by When the deadline is due and any other information
     */
    public Deadline(String taskDescription, boolean isCompleted, String by) {
        super(taskDescription, isCompleted);
        this.by = by;
    }

    /**
     * @inheritDocs
     */
    @Override
    public String getTaskCode() {
        return TASK_CODE;
    }

    /**
     * @inheritDocs
     */
    @Override
    public String getExtraInfo() {
        return this.by;
    }

    /**
     * @inheritDocs
     */
    @Override
    public String toString() {
        return "[" + getTaskCode() + "]" + super.toString() + " (by: " + by + ")";
    }
}
