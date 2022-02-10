public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns a String representing the description for the Task.
     *
     * @return Description of Task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a character that appropriately represents the state of Task completion
     *
     * @return char 'X' if done, ' ' if not done.
     */
    public char getStatusIcon() {
        return (this.isDone ? 'X' : ' ');
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