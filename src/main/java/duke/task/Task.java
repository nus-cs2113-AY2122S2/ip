package duke.task;

/**
 * Indicates the task description and whether it is done.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String info;
    protected char taskType;

    /**
     * Gets the task type of the corresponding task.
     *
     * @return The task type
     */
    public char getTaskType() {
        return taskType;
    }

    /**
     * The constructor for each task, taking in and storing its description
     * and sets isDone to false.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done or not done yet.
     *
     * @param done The indication whether the task is done or not done yet
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks if the task is done and returns an indication.
     *
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        if (isDone == true) {
            return "X";
        }
        else {
            return " ";
        }
    }

    /**
     * Returns the string which represents all information of the task
     * to be printed to the standard output.
     *
     * @return A string consisting of all the information of the task to be printed
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task
     */
    public String saveDescription() {
        return description;
    }

    /**
     * Gets the information of the task.
     *
     * @return The information of the task
     */
    public String saveAdditionalInfo() {
        return info;
    }
}
