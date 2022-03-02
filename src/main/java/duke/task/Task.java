package duke.task;

public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Assumption: Every field must be present and not null.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Assumption: Every field must be present and not null.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description in String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the current task as either done or not done
     * @param done The status of the task in boolean
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The task status icon in String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the current status of task.
     *
     * @return The status of Task in Boolean.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns the type of task in String.
     *
     * @return The type of task as String.
     */
    public abstract String getTaskType();

    /**
     * Returns the date of task in formatted String.
     *
     * @return The date of task in String format.
     */
    public abstract String getDateFormattedString();

    /**
     * Returns the Date in its original format as String.
     *
     * @return Date of task in unformatted String.
     */
    public abstract String getDateForStorageFile();

    /**
     * Returns the whole string
     * including the task icon and
     * task description to be printed.
     *
     * @return The task icon and task description in String.
     */
    @Override
    public String toString(){
        return "["+getStatusIcon()+"] " +getDescription();
    }
}
