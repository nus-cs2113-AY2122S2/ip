package duke;

/**
 * This class represent a Task that need to be done.
 */
public class ToDo extends Task {
    protected boolean isDone;

    /**
     * Store the description of the given ToDo object
     * @param description
     */
    public ToDo(String description) {
        super(description);
        isDone = false;
    }

    /**
     * mark the ToDo object as completed.
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * mark the ToDo object as incomplete.
     */
    @Override
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * A String representation of the given ToDo object.
     * @return the String representation of the given ToDo object.
     */
    @Override
    public String toString() {
        String status = null;
        if (isDone) {
            status = "X";
        } else {
            status = " ";
        }
        return String.format("[T][%s] %s", status, super.toString());
    }
}
