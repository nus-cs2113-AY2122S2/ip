public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task object
     *
     * @param description Description of the task
     * @return the Task object
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the Task Description
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task status
     * @param status The status (true/false) of whether the task is done
     */
    public void setDone(boolean status) {
        isDone = status;
    }

    /**
     * Gets the Task Status Icon (X if done, "" if not)
     * @return the status icon
     */
    public String getStatusIcon() {
        String s = (isDone ? "X" : " ");
        return s;
    }
}
