package duke.task;

/**
 * Describes a task which stores the description
 * and when the task deadline is.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * The constructor which sets the given description of the task
     * and its deadline, and sets the task type accordingly.
     *
     * @param description The description of the task
     * @param by The deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = 'D';
    }

    /**
     * Returns the string which represents all information of the task
     * to be printed to the standard output.
     *
     * @return A string consisting of all the information of the task to be printed
     */
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task
     */
    public String saveDescription() {
        return super.saveDescription();
    }

    /**
     * Gets the information of the task.
     *
     * @return The information of the task
     */
    public String saveAdditionalInfo() {
        return "/by" + by;
    }
}
