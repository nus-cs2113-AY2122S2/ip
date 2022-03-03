package duke;

import java.io.Serializable;

/**
 * A description of the task object
 */
abstract class Task {
    protected String description;

    /**
     * A constructor to store the description of the task object.
     * @param item
     */
    public Task(String item) {
        this.description = item;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Abstract method to mark the task as completed.
     */
    abstract public void markAsDone();

    /**
     * Abstract method to mark the task as incomplete.
     */
    abstract public void markAsNotDone();

    /**
     * A String representation of the Task object.
     * @return the String representation of the Task object
     */
    @Override
    public String toString() {
        return this.description;
    }
}
