package Duke.Tasks;

/**
 * Represents a task.
 */
public class Task {
    public String description;
    protected boolean isDone;

    /**
     * Constructs a task object.
     *
     * @param description The description of the task.
     * @param isDone Whether the task has been done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        if (isDone) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Assigns a character if the task is done.
     *
     * @return "X" if the task is done, and " " if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Represent that the task is done.
     *
     * @return true as task is marked as done.
     */
    public boolean markAsDone() {
        return isDone = true;
    }

    /**
     * Represent that the task is not done.
     *
     * @return false as task is marked as not done.
     */
    public boolean unMark() {
        return isDone = false;
    }

    public String saveTasks() {
        int isMarked = 0;
        if (this.isDone) {
            isMarked = 1;
        }
        return "| " + isMarked + " | " + this.description ;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

