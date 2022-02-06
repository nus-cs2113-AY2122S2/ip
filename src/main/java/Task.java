public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create a Task with isDone set to false and empty description
     */
    public Task() {
        this.description = "";
        this.isDone = false;
    }

    /**
     * Create a Task with isDone set to false and description of choice
     *
     * @param description Description of the Task to be created
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String representing the description for the Task.
     *
     * @return Description of Task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a character that appropriately represents the state of Task completion
     *
     * @return char 'X' if done, ' ' if not done.
     */
    public char getStatusIcon() {
        return (isDone ? 'X' : ' ');
    }

    /**
     * Sets a boolean value for isDone
     *
     * @param isDone boolean value representing completion of Task
     */
    public void setStatusIcon(boolean isDone) {
        this.isDone = isDone;
    }
}