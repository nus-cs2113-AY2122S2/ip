package data;

/**
 * Represents a general task with only description.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Class constructor specifying the task description.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Class constructor specifying the task description and whether the task is done.
     *
     * @param description description of the task
     * @param isDone whether the task is done
     */
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {

        return description;
    }

    public boolean isDone() {

        return isDone;
    }

    public void markAsDone() {

        isDone = true;
    }

    public void unmark() {

        isDone = false;
    }

    /**
     * Get the icon indicating whether the task is done.
     *
     * @return "X" if the task is done.
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Get information of the task that can be stored in data file.
     *
     * @return information about the task.
     */
    public String getInfo() {
        return "U / " + isDone + " / " + description;
    }
}
