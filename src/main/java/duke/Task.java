package duke;

/**
 * Represents the base class of a task.
 * Contains a task title and an isDone attributes.
 */
public class Task {
    String title;
    boolean isDone;

    /**
     * Constructs a task object.
     * Sets a newly created task as not completed yet by default.
     * @param title The title of the task that is to be created.
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Gets the isDone attribute of a task.
     * @return Attribute that signifies task completion.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the isDone attribute of a task.
     * @param done Used to set the isDone attribute
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Gets the task completion status of a task.
     * @return String that represents the appropriate characters to denote task completion.
     */
    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public String getTitle() {
        return title;
    }

    /**
     * Converts a task to its string representation of a task and its attributes.
     * @return String representation of a task and its attributes.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",this.getStatusIcon(),this.getTitle());
    }
}
