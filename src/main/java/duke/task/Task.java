package duke.task;

/**
 * Represents a task that contain its description, whether the task is done and the type of task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeOfTask;

    public Task(String description, String typeOfTask) {
        this.description = description;
        this.isDone = false;
        this.typeOfTask = typeOfTask;
    }

    /**
     * This is the getStatusIcon method that returns the icon that represents whether a task is done.
     *
     * @return The string "X" when a task is done and an empty string when a task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : ""); // mark done task with X
    }

    /**
     * This is the markAsDone method that marks the task as done and saves it in this task.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This is the markAsUndone method that marks the task as not done and saves it in this task.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * This is the getDescription method that returns the description of this task.
     *
     * @return The description of this task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This is the isDone method that returns whether this task is done.
     *
     * @return True if this task is done and false if this task is not done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * This is the getTypeOfTask method that returns the type of task for this task.
     *
     * @return The type of task for this task
     */
    public String getTypeOfTask() {
        return typeOfTask;
    }

    /**
     * This is the toString method that returns a particular format to represent this task.
     *
     * @return The printing format of this task.
     */
    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}
