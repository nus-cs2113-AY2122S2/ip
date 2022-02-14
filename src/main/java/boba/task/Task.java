package boba.task;

/**
 * Represents a task that needs to be done.
 */
public class Task {

    /** The description of the task */
    protected String description;
    /** Track if the task is completed */
    protected boolean isDone;

    /**
     * Creates a new Task with the given description.
     * The task initially set to not completed
     * @param description A description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark a task as completed
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark a task as not completed
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Get the description of the task
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get if the task is done
     * @return true if finished
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @return String representation of Task class
     */
    @Override
    public String toString() {
        String marked = isDone ? "[X] " : "[ ] ";
        return marked + description;
    }
}
