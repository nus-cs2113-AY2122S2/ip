package duke.task;

/**
 * Represents a task that the user has to do
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs the task
     * 
     * @param description
     *            description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs the task
     * 
     * @param description
     *            description of the task
     * @param isDone
     *            status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Get icon representation of the task status
     * 
     * @return String representation of the icon
     */
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    /**
     * Setter for done status
     * 
     * @param isDone
     *            status to set the task to
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Getter for the task
     * 
     * @return string representation of the task
     */
    public String getTask() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the save file format string of the task
     */
    @Override
    public String toString() {
        return this.isDone ? "1" : "0" + " | " + this.description;
    }
}
