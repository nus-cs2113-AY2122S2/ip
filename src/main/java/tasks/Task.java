package tasks;

/**
 * An abstract representation of a task. This class should not be instantiated.
 * Instead, use the specific Task subclasses: Deadline, Event, or Todo.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static final String BLANK_CHECKBOX = "[ ]";
    protected static final String DONE_CHECKBOX = "[X]";

    /**
     * Constructs a Task instance with a specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the Task instance.
     *
     * @return The description of the Task instance.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status of the Task instance. That is, whether the task is marked as done or not.
     *
     * @return true if the Task instance is marked as done. Otherwise, false is returned.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets the status of the Task instance. That is, whether the task is marked as done or not.
     *
     * @param done Indicates whether the task is marked as done or not.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Gets a string representation of a checkbox to indicate if the task is marked as done or not.
     *
     * @return A string representation of a checkbox of the Task instance.
     */
    protected String getCheckbox() {
        return isDone() ? DONE_CHECKBOX : BLANK_CHECKBOX;
    }

    /**
     * Returns a string representation of the Task instance.
     *
     * @return A string representation of the Task instance.
     */
    public String toString() {
        String checkbox = getCheckbox();

        return String.format("%s %s", checkbox, getDescription());
    }
}
