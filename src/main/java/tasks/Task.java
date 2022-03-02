package tasks;

/**
 * This class handles the creation of Task instances.
 */
public abstract class Task {
    private String taskDescription;
    private boolean isCompleted;

    /**
     * Creates a Task instance with only the task description.
     *
     * @param taskDescription Description of the task
     */
    public Task(String taskDescription) {
        setTaskDescription(taskDescription);
        setCompleted(false);
    }

    /**
     * Creates a Task instance with task description and its completion status
     * @param taskDescription Description of the task
     * @param isCompleted Completion status of the task (ie true if done; false otherwise)
     */
    public Task(String taskDescription, boolean isCompleted) {
        setTaskDescription(taskDescription);
        setCompleted(isCompleted);
    }

    /**
     * Returns the task description.
     *
     * @return taskDescription
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Sets the task description.
     *
     * @param taskDescription Description of the task
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Returns the completion status of a task.
     *
     * @return isCompleted
     */
    public boolean getCompletionStatus() {
        return isCompleted;
    }

    /**
     * Sets the completion status of a task.
     *
     * @param isCompleted Completion status of the task (ie true if done; false otherwise)
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Converts the Task object into a printable String when printing out a task.
     *
     * @return String with relevant data for the task
     */
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + getTaskDescription();
    }

    /**
     * Returns the task code which is a character to represent what type of task it is.
     *
     * @return TASK_CODE
     */
    public abstract String getTaskCode();

    /**
     * Returns extra information for the task if it has any.
     *
     * @return Extra information (ie the "by" for deadline or "at" for events)
     */
    public abstract String getExtraInfo();
}
