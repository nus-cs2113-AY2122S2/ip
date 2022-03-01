package duke.task;

/**
 * Represents a task that contain its description, whether the task is done and the type of task.
 */
public class Task {
    private String description;
    private boolean isDone;
    private String typeOfTask;

    public Task(String description, String typeOfTask) {
        this.description = description;
        this.isDone = false;
        this.typeOfTask = typeOfTask;
    }

    /**
     * Returns the icon that represents whether a task is done.
     *
     * @return The string "X" when a task is done and an empty string when a task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : ""); // mark done task with X
    }

    /**
     * Marks the task as done and saves it in this task.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done and saves it in this task.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return The description of this task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if this task is done.
     *
     * @return True if this task is done and false if this task is not done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the type of task for this task.
     *
     * @return The type of task for this task.
     */
    public String getTypeOfTask() {
        return typeOfTask;
    }

    /**
     * Returns a particular format to represent this task.
     *
     * @return The printing format of this task.
     */
    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}
