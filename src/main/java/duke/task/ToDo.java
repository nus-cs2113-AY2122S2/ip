package duke.task;

/**
 * Describes a to do task.
 */
public class ToDo extends Task {

    /**
     * The constructor which sets the given description to the task
     * and assigns the task type.
     *
     * @param description The task description
     */
    public ToDo(String description) {
        super(description);
        this.taskType = 'T';
    }

    /**
     * Returns the string which represents all information of the task
     * to be printed to the standard output.
     *
     * @return A string consisting of all the information of the task to be printed
     */
    public String toString() {
        return "[T]" + super.toString();
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
        return "";
    }
}
