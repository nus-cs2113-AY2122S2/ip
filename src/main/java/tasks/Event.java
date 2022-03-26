package tasks;

/**
 * This class handles the creation of Event instances.
 */
public class Event extends Task {
    private static final String TASK_CODE = "E";
    protected String at;

    /**
     * Creates an Event instance with only a task description and its required extra information.
     *
     * @param taskDescription Description of the event task
     * @param at Where the event is being held and any other info
     */
    public Event(String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }

    /**
     * Creates an Event instance with a task description, its required extra information
     * and its completion status.
     *
     * @param taskDescription Description of the event task
     * @param isCompleted Completion status of the task (ie true if done; false otherwise)
     * @param at Where the event is being held and any other info
     */
    public Event(String taskDescription, boolean isCompleted, String at) {
        super(taskDescription, isCompleted);
        this.at = at;
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
        return this.at;
    }

    /**
     * @inheritDocs
     */
    @Override
    public String toString() {
        return "[" + getTaskCode() + "]" + super.toString() + " (at: " + at + ")";
    }
}
